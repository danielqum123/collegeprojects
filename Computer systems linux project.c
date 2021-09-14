/*DANIAL QUMSIEH
* SWER252 - OPERATING SYSTEMS
*/

#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <stdlib.h>
#include <string.h>
#include <sys/wait.h>
#include <errno.h>
#include <ctype.h>
#include <dirent.h>
#include <fcntl.h>
#include <time.h>
#include <sys/stat.h>
#define FORMAT "%6s %s\t%8s %s\n"

int file_no_of_lines=500;

char * path;


void pwdCommand()   //function for pwd command
{

    char s[100];
    printf("%s\n", getcwd(s, 100));
}


void cdCommand(char** args)   //function for Cd command
{

    char s[100];
    if(args[1]==NULL)
    {
        chdir(".."); // changing directory
        printf("%s\n", getcwd(s, 100));
        return;
    }

    chdir(args[1]);// changing directory
    printf("%s\n", getcwd(s, 100));
}




void psCommand(char** args)
{

    struct dirent *ent;
    int i, fd_self, fd;
    unsigned long time, stime;
    char flag, *tty;
    char cmd[256], tty_self[256], path[256], time_s[256];
    FILE* file;

    DIR *dir = opendir("/proc");
    fd_self = open("/proc/self/fd/0", O_RDONLY);
    sprintf(tty_self, "%s", ttyname(fd_self));
    printf(FORMAT, "PID", "TTY", "TIME", "CMD");

    while ((ent = readdir(dir)) != NULL)
    {
        flag = 1;
        for (i = 0; ent->d_name[i]; i++)
            if (!isdigit(ent->d_name[i]))
            {
                flag = 0;
                break;
            }

        if (flag)
        {
            sprintf(path, "/proc/%s/fd/0", ent->d_name);
            fd = open(path, O_RDONLY);
            tty = ttyname(fd);

            if ((tty && strcmp(tty, tty_self) == 0 )|| (args[1] !=NULL && strcmp(args[1],"-A")==0))
            {

                sprintf(path, "/proc/%s/stat", ent->d_name);
                file = fopen(path, "r");
                fscanf(file, "%d%s%c%c%c", &i, cmd, &flag, &flag, &flag);
                cmd[strlen(cmd) - 1] = '\0';

                for (i = 0; i < 11; i++)
                    fscanf(file, "%lu", &time);
                fscanf(file, "%lu", &stime);
                time = (int)((double)(time + stime) / sysconf(_SC_CLK_TCK));
                sprintf(time_s, "%02lu:%02lu:%02lu",
                        (time / 3600) % 3600, (time / 60) % 60, time % 60);
                if(tty)
                {
                    printf(FORMAT, ent->d_name, tty + 5, time_s, cmd + 1);
                }
                else
                    printf(FORMAT, ent->d_name, "?     ", time_s, cmd + 1);
                fclose(file);
            }
            close(fd);
        }
    }
    close(fd_self);


}


void killCommand(char** args)   //function for kill command
{
    int pid,sigId=SIGTERM;
    if(args[1]!=NULL && strcmp(args[1], "-l") == 0 )
    {
        printf("1) SIGHUP  9) SIGKILL  15) SIGTERM \n");
    }
    else if(args[1]!=NULL && args[2]!=NULL)
    {
        if(strcmp(args[1], "SIGHUP") == 0)
            sigId=1;
        else if(strcmp(args[1], "SIGKILL") == 0)
            sigId=9;
        else if(strcmp(args[1], "SIGTERM") == 0)
            sigId=15;
        else
            sigId=atoi(args[1]);
        pid=atoi(args[2]);
        if (kill(pid,sigId)==0)
            printf("Signal sent!\n");

    }
    else if(args[1]!=NULL && args[2]==NULL)
    {
        pid=atoi(args[1]);

        if (kill(pid,sigId)==0)
            printf("Killed!\n");
    }
}







int internal_command(char **args)
{
    if(strcmp(args[0], "exit") == 0)   // if exit command enters
    {
        exit(0);
    }

    if(strcmp(args[0], "cd") == 0)  // if cd Command enters
    {
        cdCommand(args);
        return 1;
    }
    if(strcmp(args[0], "pwd") == 0) // if pwd Command enters
    {
        pwdCommand();
        return 1;
    }
    if(strcmp(args[0], "ps") == 0) // if ps Command enters
    {
        psCommand(args);
        return 1;
    }
    if(strcmp(args[0], "kill") == 0) // if kill Command enters
    {
        killCommand(args);
        return 1;
    }


    return 0;
}


char **getFullLine()
{

    //Take input
    int lineSize=500;
    char* line= (char *)malloc(lineSize * sizeof(char));
    fgets(line,lineSize,stdin);




//removal of endl as fgets also store \n
    int j=0;
    while(line[j]!='\0')
    {
        j++;
    }
    j--;
    line[j]='\0';

//parsing
    char** arg=(char **)malloc(500 * sizeof(char*));
    char* tok;
    // Use of strtok
    // get first token
    tok = strtok(line," ");
    int i=0;
    // Checks for delimeter
    while (tok != 0)
    {
        arg[i] = strdup(tok);
        // Use of strtok
        // go through other tokens
        tok = strtok(0," ");
        i++;
    }
    arg[i] = NULL;
    free(line);
    return arg;
}



char **pathParsing()
{
//parsing
    char** arg=(char **)malloc(20 * sizeof(char*));
    char* tok;
    // Use of strtok
    // get first token
    tok = strtok(path,":");
    int i=0;
    // Checks for delimeter
    while (tok != 0)
    {
        arg[i] = strdup(tok);
        // Use of strtok
        // go through other tokens
        tok = strtok(0,":");
        i++;
    }
    arg[i] = NULL;
    return arg;
}


int redirect_input(char **args, char **input_filename)
{
    int i;
    int j;

    for(i = 0; args[i] != NULL; i++)
    {

        // Look for the <
        if(args[i][0] == '<')
        {
            free(args[i]);

            // Read the filename
            if(args[i+1] != NULL)
            {
                *input_filename = args[i+1];
            }
            else
            {
                return -1;
            }

            // Adjust the rest of the arguments in the array
            for(j = i; args[j-1] != NULL; j++)
            {
                args[j] = args[j+2];
            }

            return 1;
        }
    }

    return 0;
}

/*
 * Check for output redirection
 */
int redirect_output(char **args, char **output_filename)
{
    int i;
    int j;

    for(i = 0; args[i] != NULL; i++)
    {

        // Look for the >
        if(args[i][0] == '>')
        {
            free(args[i]);

            // Get the filename
            if(args[i+1] != NULL)
            {
                *output_filename = args[i+1];
            }
            else
            {
                return -1;
            }

            // Adjust the rest of the arguments in the array
            for(j = i; args[j-1] != NULL; j++)
            {
                args[j] = args[j+2];
            }

            return 1;
        }
    }

    return 0;
}


int Check_for_pipe(char **args,char** left)
{
    int i;
    for(i=0; args[i] != NULL; i++)
    {

        // Look for the |
        if(args[i][0] == '|')
        {
            free(args[i]);


            // Get the left

            int j;
            for(j=0; j<i; j++)
            {
                if(args[j] != NULL)
                {
                    left[j] = args[j];
                }
                else
                {
                    return -1;
                }
            }
            left[j] = NULL;

            // Adjust the rest of the arguments in the array
            int k;
            for(j = i+1,k=0; args[j] != NULL; j++,k++)
            {
                args[k] = args[j];
            }
            args[k] = NULL;

            return 1;
        }
    }
    return 0;
}


int only_check_for_pipe(char **args)
{
    int i;
    for(i=0; args[i] != NULL; i++)
    {

        // Look for the |
        if(args[i][0] == '|')
        {
            return 1;
        }
    }
    return 0;

}


void Check_for_redirection(int* input,int* output,char **args,char **input_filename,char **output_filename)
{


// Check for redirected input
    *input = redirect_input(args, input_filename);

    switch(*input)
    {
    case -1:
        printf("Syntax error!\n");
        //continue;
        break;
    case 0:
        break;
    case 1:
        printf("Redirecting input from: %s\n", *input_filename);
        break;
    }

    // Check for redirected output
    *output = redirect_output(args, output_filename);

    switch(*output)
    {
    case -1:
        printf("Syntax error!\n");
        //continue;

        break;
    case 0:
        break;
    case 1:
        printf("Redirecting output to: %s\n", *output_filename);
        break;
    case 2:
        printf("Appending output to the end of %s\n",*output_filename);
        break;


    }


}

int command(char **args)
{

    int result,result1;
    pid_t child_id,child_id1;
    int status,status1;
    int p[2];

    char ** left = (char **)malloc(100 * sizeof(char *));

    if(args==NULL)
    {
        exit(-1);
    }

    if(only_check_for_pipe(args)==1)
    {

        Check_for_pipe(args, left);

        pipe(p);

        // Fork the child process
        child_id = fork();

        // Check for errors in fork()
        switch(child_id)
        {
        case EAGAIN:
            perror("Error EAGAIN: ");
            return 0;
        case ENOMEM:
            perror("Error ENOMEM: ");
            return 0;
        }

        if(child_id == 0)
        {
            int input=0;
            int output=0;
            char* output_filename=NULL;
            char* input_filename=NULL;


            Check_for_redirection(&input,&output,left,&input_filename, &output_filename);
            // Set up redirection in the child process
            if(input)
                freopen(input_filename, "r", stdin);

            if(output==1)
                freopen(output_filename, "w+", stdout);
            if (output == 2)
                freopen(output_filename, "a", stdout);

            close(1);
            dup(p[1]);
            close(p[0]);
            close(p[1]);

            // Execute the command
            char **paths=pathParsing();
            int k=0;
            while(paths[k]!=NULL)
            {
                result = execv(strcat(paths[k],left[0]),left);
                free(paths[k]);
                k++;
            }
            free(paths);

            exit(-1);
        }

        // Wait for the child process to complete
        // printf("Waiting for child, pid = %d\n", child_id);
        result = waitpid(child_id, &status, 0);


        child_id1 = fork();
        // Check for errors in fork()
        switch(child_id1)
        {
        case EAGAIN:
            perror("Error EAGAIN: ");
            return 0;
        case ENOMEM:
            perror("Error ENOMEM: ");
            return 0;
        }

        if(child_id1 == 0)
        {
            close(0);
            dup(p[0]);
            close(p[0]);
            close(p[1]);
            command(args);
            exit(0);
        }


        close(p[0]);
        close(p[1]);
//	printf("Waiting for child, pid = %d\n", child_id1);
        result1 = waitpid(child_id1, &status1, 0);


    }


    else
    {


        // Fork the child process
        child_id = fork();

        // Check for errors in fork()
        switch(child_id)
        {
        case EAGAIN:
            perror("Error EAGAIN: ");
            return 0;
        case ENOMEM:
            perror("Error ENOMEM: ");
            return 0;
        }

        if(child_id == 0)
        {

            int input=0;
            int output=0;
            char *output_filename=NULL;
            char *input_filename=NULL;

            Check_for_redirection(&input,&output,args,&input_filename, &output_filename);

//printf("Output= %d \n",output);
            // Set up redirection in the child process
            if(input)
            {
                freopen(input_filename, "r", stdin);
            }

            if(output==1)
            {
                freopen(output_filename, "w+", stdout);
            }

            if (output == 2)
            {
                freopen(output_filename, "a+", stdout);
            }


            // Execute the command
            char **paths=pathParsing();
            int k=0;
            while(paths[k]!=NULL)
            {
                result = execv(strcat(paths[k],args[0]),args);
                free(paths[k]);
                k++;
            }
            printf("\n'offending' command name.\n");
            free(paths);

            exit(-1);
        }

        // Wait for the child process to complete, if necessary
//	printf("Waiting for child, pid = %d\n", child_id);
        result = waitpid(child_id, &status, 0);

    }

}

void main()
{

    path= (char *)malloc(100 * sizeof(char));

    //initilize paths
    strcat(path,"/bin/");
    strcat(path,":");
    strcat(path,"/usr/bin/");
    strcat(path,":");

    char **input=NULL;

    while(1)
    {

        // Print out the prompt and get the input
        printf("\n$>  ");
        if(input!=NULL)
        {

        }
        input = getFullLine();

        //Validate input
        if(input==NULL)
        {
            continue;
        }
        if(input[0] == NULL)
            continue;


        if(internal_command(input)) // Check if enternal command is entered
            continue;
        //Run Command
        command(input);
    }
}
