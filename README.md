# Assignment 3: _"Extra Motivated"_

## Run
Use the drop-down run presets through IntelliJ.

Alternatively, run `$ mvn exec:java -Dexec.mainClass=ex##.App` where `##` is the exercise number.

## Test
Use the drop-down run presets through IntelliJ.

Alternatively, run `$ mvn test` to test everything.

### Missing test?
 > The package `ex43` appears to be missing a valid testcase!

True, but not true. Package `ex43` uses `shared.io.FileIO` extensively,
which has its own test cases.

Also, in case you missed it, the test suites for `ex41` and `ex45` don't actually test any classes or methods
written by me! Instead, I decided to implement tests for the standard library Java stuff that I used, to mock
what I would write had I written those methods.
