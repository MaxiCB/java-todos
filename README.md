# Java Todo's API

## Object Schema

### Users
| Field     | Type   | Notes                        |
|-----------|--------|------------------------------|
| UserID    | Long   | Primary Key - Auto Generated |
| Username  | String | Required - Unique Value      |
| Password  | String | Required                     |
| Email     | String | Required - Email Format      |
| UserRoles | List<> | Array of valid Roles         |
| UserTodos | List<> | Array of Todos               |

### Todo

| Field       | Type   | Notes                        |
|-------------|--------|------------------------------|
| TodoID      | Long   | Primary Key - Auto Generated |
| Description | String | Required                     |
| Datestarted | Date   | Required                     |
| Completed   | Bool   | Optional - Default to false  |

### Role

| Field  | Type   | Notes                        |
|--------|--------|------------------------------|
| RoleID | Long   | Primary Key - Auto Generated |
| Name   | String | Required - Unique            |

## API

BASE URL: -Need To Deploy-

SeedData Implemented for testing

#### Table of Contents

| Type | Path                               | Notes                                | Example |
|------|------------------------------------|--------------------------------------|---------|
| GET  | /users/users                       | Fetch all users                      |         |
| GET  | /users/users/{userid}              | Fetch a user by UserID               |         |
| GET  | /users/users/{name}                | Fetch a user by Userame              |         |
| GET  | /users/todos/{userid}              | Fetch user todos by UserID           |         |
| POST | /users/user                        | Add a new User                       |         |
| PUT  | /users/user/{userid}               | Update a existing User               |         |
| DEL  | /users/user/{userid}               | Delete a User and all related Todo's |         |
| DEL  | /users/user/{userid}/role/{roleid} | Delete a User's Role                 |         |
| POST | /users/user/{userid}/role/{roleid} | Add a User Role                      |         |
| POST | /users/todo/{userid}               | Add a User Todo                      |         |
| GET  | /todos/todos                       | Fetch All Todo's                     |         |
| PUT  | /todo/{todoid}                     | Updates a Todo                       |         |
|      |                                    |                                      |         |
|      |                                    |                                      |         |

