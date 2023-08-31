# Kotlin Fuzzer
## How to launch

`gradlew core:runCoordinator` -- launches central verticle `com.stepanov.bbf.bugfinder.coordinator.Coordinator` 
managing all the fuzzing process.

Then you should launch the compiler verticle, you want to fuzz: 
either `gradlew JVMCompiler:run` or `gradlew NativeCompiler:run`

Now the server is started and you can start fuzzing by sending request with fuzzing settings. Examples of that you can see at `mutationRequests` 

You can also send request to `http://localhost:8888/filter-results` address to filter (deduplicate) results

### NativeCompiler

To launch NativeCompiler verticle you should specify at `NativeCompiler/build.gradle` a path to local JAR with Kotlin/Native API (you can download it from Kotlin Github)

## To future developers


If you have any questions, feel free to reach me at [Telegram](https://t.me/olezhkabobrov) or at `olezhkabobrov@gmail.com`