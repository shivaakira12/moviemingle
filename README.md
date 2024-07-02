# MovieMingle - Project Documentation

## Overview
MovieMingle is a web application built using Spring Boot, Thymeleaf, MySQL, JavaScript, and CSS. It allows users to watch free, high-quality movies.

## Technologies Used
- **Spring Boot:** Java-based framework for building web application
- **Thymeleaf:** Java-based template engine for creating dynamic web content
- **MySQL:** Relational database management system
- **JavaScript:** Programming language for web development
- **CSS:** Stylesheet language for web development

## Project Structure
- **src/main/java:** Contains Java source code
- **src/main/resources:** Contains static resources like templates, configuration files, etc.
  - **src/main/resources/templates:** Contains Thymeleaf templates
  - **src/main/resources/static:** Contains static assets like images, JavaScript, and CSS files

## Cloning the Repository
### Prerequisites:
- Git installed
- Eclipse or STS IDE installed

### Steps:
1. Open a terminal or command prompt.
2. Navigate to the desired directory where you want to clone the repository.
3. Run the following command:


git clone https://github.com/shivaakira12/moviemingle.git


### Importing Images
Note: Replace `[image_path]` with the actual path to your image file.

1. Create the following directories in your project's `src/main/resources/static` folder:
- `images/admin`
- `images/home`
- `images/add_movie`
- `images/user`

2. Copy the following images to their respective directories:
- Admin Page Home Page Image: `src/main/resources/static/images/admin/[image_name].jpg`
- Add Movie Page Image: `src/main/resources/static/images/add_movie/[image_name].jpg`
- User Page Home Page Image: `src/main/resources/static/images/user/[image_name].jpg`

### Running the Application
### Prerequisites:
- Java JDK installed
- MySQL database configured

### Steps:
1. Configure database connection details in the `application.properties` file.
2. Run the Spring Boot application as a Java application.
