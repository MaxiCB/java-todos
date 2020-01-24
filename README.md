# Java Todo's API

##Object Schema

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

| Type | Path         | Notes           | Example |
|------|--------------|-----------------|---------|
| GET  | /users/users | Fetch all users |         |