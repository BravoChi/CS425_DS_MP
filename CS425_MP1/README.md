# Fall17 CS425 MP1 README
by Siyu Bian(siyub2) and Wenzhuang Chi(wc4)

## Instructions
### Step 1 - Preparation
Six java files should be cloned to each vm machine, and a Config.data file should be set up. The following are the steps to set up.
1. ssh into each vm machine you want to use.
2. Type ```git clone https://gitlab-beta.engr.illinois.edu/wc4/CS425_MP1.git``` to clone 
3. Build a file called ```Config.data``` and added information of all machines you want to use. The format should be: ```vmNum vmAddress vmPort```
For example: ```1 127.0.0.1 2000``` 
And to access ports successfully, the port number should be larger than 1023
4. Copy ```Config.data``` to every using machine. The file should locate at the same file with java files. 


### Step 2 - Compile and launch Server
The java files should be compiled first and we wrote a script to help.
1. Give permission to run the script. Open cmd and input ```chmod +x compile.sh```
2. Compile java files and launch Server. Input ```./compile.sh```

### Step 3 Run grep client
After servers have been launched, choose any vm machine as the grep client and launch the client. Then you can use the grep function
1. Open the cmd and input ```java Client```
2. Just input the pattern you want to grep. For example, you can input ```edu.au```
3. The result will display on the cmd and if you want to do another grep, just repeat Step 3
4. The result only contains count information, if you want to know the detail grep result, go to the local of each machine, and there should be a txt file named with your pattern.