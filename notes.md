### SpecKit sample project
Spec Kit: https://github.com/github/spec-kit

step 1 - run "specify init ." in a new project folder to initialize.
Eun opencode, connect to openrouter, select an image ( qwen/qwen3-coder-30b-a3b-instruct) and issue the speckit commands shown in the next steps.

step 2 - /speckit.constitution Create principles focused on code quality, testing standards, user experience consistency, and performance requirements

step 3 - /speckit.specify Build an application that can help me organize my contacts.

Bought $10 credit and switched to minimax/minimax-m2.5.
Step 4 -
/speckit.plan The application has three tiers, 1 frontend, 2 backend and database. The frontend uses Vite with minimal number of libraries. Use vanilla HTML, CSS, and JavaScript as much as possible. The backend is written in Java 25 / Spring Boot 4 using functional programming style as much as possible guided by Scala 3 projects, ie. use Algebraic Data Type for the domain objects, pure functions, etc. The database is postgres. Setup docker image for all the tiers and create docker compose command to bring up the application to run it locally.

Step 5
/speckit.tasks

Step 6
/speckit.implement

Credit is down to $9.48

### Troubleshooting start up error.
Verified that the frontend and backend projects compile.
However running  'docker compose up' failed with the following error.


'''txt
28.98 [INFO] Recompiling the module because of changed source code.
29.00 [INFO] Compiling 16 source files with javac [debug parameters release 25] to target/classes
29.00 [INFO] -------------------------------------------------------------
29.00 [ERROR] COMPILATION ERROR : 
29.00 [INFO] -------------------------------------------------------------
29.00 [ERROR] No compiler is provided in this environment. Perhaps you are running on a JRE rather than a JDK?
29.00 [INFO] 1 error
29.00 [INFO] -------------------------------------------------------------
29.00 [INFO] ------------------------------------------------------------------------
29.00 [INFO] BUILD FAILURE
29.00 [INFO] ------------------------------------------------------------------------
29.00 [INFO] Total time:  18.469 s
29.00 [INFO] Finished at: 2026-02-15T04:01:17Z
29.00 [INFO] ------------------------------------------------------------------------
29.01 [ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.14.1:compile (default-compile) on project contacthub: Compilation failure
29.01 [ERROR] No compiler is provided in this environment. Perhaps you are running on a JRE rather than a JDK? 
'''

Reported it to minimax.
The build is fixed, but the screen showed no records. Asked it to preload 
sample data. Still no records were shown.

After few more prompts about this error, the issue was fixed.

Credit is at $9.15.

### End

### Scala 3 backend
Created plan for Scala Scala 3 tagless final style backend