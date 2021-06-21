#*** Build Stage for Spring boot application image ***

#---------------------------------------------------------------
#I am taking the base image as `openjdk:8-jdk-alpine` add the target or stage name as `build`
#---------------------------------------------------------------
FROM openjdk:8-jdk as build

#----------------------------------------------------------------
#Specify the working directory named app and switch to it
#----------------------------------------------------------------
WORKDIR /app

#---------------------------------------------------------------
#Copy the mvnw maven executable wrapper file and .mvn maven repository and configuration folder to /app directory.
#Also copy the pom.xml to /app directory
#---------------------------------------------------------------
COPY mvnw ./
COPY .mvn ./.mvn
COPY pom.xml ./
#COPY wait-for-it.sh .

#---------------------------------------------------------------
#Mark .mvnw file as executable
#---------------------------------------------------------------
RUN chmod +x ./mvnw

#----------------------------------------------------------------
#Download the dependency if needed or if the pom file is changed
#:go-offline --> ensure you have all of your dependencies installed locally before you begin to work offline
#----------------------------------------------------------------
RUN ./mvnw dependency:go-offline -B

#---------------------------------------------------------------
#Copy application code
#---------------------------------------------------------------
COPY src src
COPY output_html output_html
COPY output_pdf output_pdf
COPY output_xml output_xml
COPY output_metadata output_metadata

#--------------------------------------------------------------------
#This will create the fat jar for my application, -DskipTests will skip the test for application during the building package
#--------------------------------------------------------------------
RUN ./mvnw package -DskipTests

#--------------------------------------------------------------------
#Create the `target/dependency` directory and go to that directory.
#`jar -xf ../*.jar` will extract the fat jar, create seperate jar for every dependency and create the small jar for application.
#--------------------------------------------------------------------
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# ***Production Stage for Spring boot application image**
FROM openjdk:11-jre as production
RUN apt-key adv --keyserver keyserver.ubuntu.com --recv-keys 8B48AD6246925553 7638D0442B90D010 CBF8D6FD518E17E1
RUN echo "deb http://httpredir.debian.org/debian jessie main contrib" > /etc/apt/sources.list \
    && echo "deb http://security.debian.org/ jessie/updates main contrib" >> /etc/apt/sources.list \
    && echo "ttf-mscorefonts-installer msttcorefonts/accepted-mscorefonts-eula select true" | debconf-set-selections \
    && apt-get update \
    && apt-get install -y ttf-mscorefonts-installer \
    && apt-get clean \
    && apt-get autoremove -y \
    && rm -rf /var/lib/apt/lists/


ARG DEPENDENCY=/app/target/dependency

# Copy the dependency application file from build stage artifact
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#COPY --from=build ./app/wait-for-it.sh .

# Run the Spring boot application
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "rs.ac.uns.ftn.tim5.ApiorganvlastiApplication"]
