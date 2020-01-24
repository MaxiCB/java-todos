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
| GET  | /todos/todos                       | Fetch All Todo's                     |         |
| POST | /users/user                        | Add a new User                       |         |
| POST | /users/user/{userid}/role/{roleid} | Add a User Role                      |         |
| POST | /users/todo/{userid}               | Add a User Todo                      |         |
| PUT  | /users/user/{userid}               | Update a existing User               |         |
| PUT  | /todos/todo/{todoid}               | Updates a Todo                       |         |
| DEL  | /todos/{todoid}                    | Deletes a Todo                       |         |
| DEL  | /users/user/{userid}               | Delete a User and all related Todo's |         |
| DEL  | /users/user/{userid}/role/{roleid} | Delete a User's Role                 |         |

## Examples

### GET /users/users

Response Data - 
```
[
    {
        "userid": 4,
        "username": "test user",
        "email": "test@test.com",
        "userroles": [
            {
                "role": {
                    "roleid": 1,
                    "name": "ADMIN"
                }
            }
        ],
        "userTodos": [
            {
                "todoid": 5,
                "description": "Example Todo!",
                "datestarted": "2020-01-24T18:19:45.876+0000",
                "completed": false
            }
        ]
    }
]
```

### GET /users/users/{userid}

Response Data - 
```
[
    {
        "userid": 4,
        "username": "test user",
        "email": "test@test.com",
        "userroles": [
            {
                "role": {
                    "roleid": 1,
                    "name": "ADMIN"
                }
            }
        ],
        "userTodos": [
            {
                "todoid": 5,
                "description": "Example Todo!",
                "datestarted": "2020-01-24T18:19:45.876+0000",
                "completed": false
            }
        ]
    }
]
```

### GET /users/users/name/{name}

Response Data - 
```
[
    {
        "userid": 4,
        "username": "test user",
        "email": "test@test.com",
        "userroles": [
            {
                "role": {
                    "roleid": 1,
                    "name": "ADMIN"
                }
            }
        ],
        "userTodos": [
            {
                "todoid": 5,
                "description": "Example Todo!",
                "datestarted": "2020-01-24T18:19:45.876+0000",
                "completed": false
            }
        ]
    }
]
```

### GET /users/todos/{userid}

Response Data -
```
[
    {
        "todoid": 5,
        "description": "Example Todo!",
        "datestarted": "2020-01-24T18:19:45.876+0000",
        "completed": false
    },
    {
        "todoid": 6,
        "description": "Finish java-orders-swagger",
        "datestarted": "2020-01-24T18:19:45.876+0000",
        "completed": false
    }
]
```

### GET /todos/todos

Response Data -
```
[
    {
        "todoid": 5,
        "description": "Example Todo!",
        "datestarted": "2020-01-24T18:19:45.876+0000",
        "completed": false
    },
    {
        "todoid": 6,
        "description": "Finish java-orders-swagger",
        "datestarted": "2020-01-24T18:19:45.876+0000",
        "completed": false
    }
]
```

### POST /users/user

Request Data -
```
{
    "username": "Example",
    "password": "Password1",
    "email": "email@email.com"
}
```
Response Data -
```
{
    "userid": 409,
    "username": "example",
    "email": "email@email.com",
    "userroles": [],
    "userTodos": []
}
```
### POST /users/user/{userid}/role/{roleid}
Need to Implemente checking before Documenting

Response Data -
```

```
### POST /users/todo/{userid}
Request Data -
```
{
    "description": "Example Todo!",
    "datestarted": "2020-01-24T19:03:30.289+0000",
    "completed": false
}
```
Response Data -
```
{
    "todoid": 409,
    "description": "Example Todo!",
    "datestarted": "2020-01-24T19:03:30.289+0000",
    "completed": false
}
```
### PUT /users/user/{userid}
Request Data -
```
{
    "username": "test user",
    "email": "test@test.com"
}
```
Response Data -
```
{
    "userid": 4,
    "username": "testing",
    "email": "test@test.com",
    "userroles": [],
    "usertodos": []
}
```
### PUT /todos/todo/{todoid}
        
Request Data -
```
{
    "description": "Example Todo!",
    "completed": true
}
```
Response Data -
```
{
    "todoid": 5,
    "description": "example todo!",
    "datestarted": "2020-01-24T19:15:08.649+0000",
    "completed": true
}
```
### DEL /todos/{todoid}
```
HttpStatus.OK - Okay Status
```
### DEL /user/user/{userid}
```
HttpStatus.OK - Okay Status
```
### DEL /users/user/{userid}/role/{roleid}
```
HttpStatus.OK - Okay Status
```