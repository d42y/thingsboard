# Development Node
## Skip license check
1. Edit pom.xml
2. Search for license-maven-plugin
3. Under configuration
4. Add <skip>true</skip> <!-- skip license header check -->
5. Keep existing code
   Looks like this
   ```text
   <plugin>
                    <groupId>com.mycila</groupId>
                    <artifactId>license-maven-plugin</artifactId>
                    <version>3.0</version>
                    <configuration>
                        <skip>true</skip> <!-- skip license header check -->
                        <header>${main.dir}/license-header-template.txt</header>
                         ...
   ```
