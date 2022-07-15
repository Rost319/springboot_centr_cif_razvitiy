# Spring Boot, HSQL, Spring Data

### Steps to Setup

##### 1. Clone the application
https://github.com/Rost319/springboot_centr_cif_razvitiy.git

##### 2. Run the app using maven
mvn spring-boot:run

The app will start running at http://localhost:8080

### Explore Rest APIs

#### News
| Method | Url | Description | Sample Valid Request Body |
|--------|-----|-------------|---------------------------|
| GET | /api/news | Get all news |   |
| GET | /api/news/{newsId} | Get news by id |   |
| GET | /api/news/news-type/{newsTypeName} | Get all news by name news type |   |
| POST | /api/news/news-type/{newsTypeId} | Add news with reference to news type | JSON |
| PUT | /api/news/{newsId}/news-type/{newsTypeId} | Update news with reference to news type | JSON |
| DELETE | /api/news/{newsId} | Delete news |   |
______________________________________________________________________________________________________

#### News type
| Method | Url | Description | Sample Valid Request Body |
|--------|-----|-------------|---------------------------|
| GET | /api/news-type | Get all news type |   |
| GET | /api/news-type/{newsTypeId} | Get news type by id |   |
| POST | /api/news-type | Add news type | JSON |
| PUT | /api/news-type/{newsTypeId} | Update news type | JSON |
| DELETE | /api/news-type/{newsTypeId} | Deleting a news type with all related news (Cascade) |   |