# UserControllerTests

## Positive
###  GET USER'S LIST
 * 1 List users without another params
    * Get /entity/{realm}/User for AUTO3N 

 * 2 List users with query criteria
   * Get /entity/{realm}/User for AUTO3N and q=test

 * 3 List users with paging
   * Get /entity/{realm}/User for AUTO3N and limit = 15 and skip = 15

 * 4 List users with sort
   * Get /entity/{realm}/User for AUTO3N and sort id by DESC

### CREATE NEW USER
* 5 Create new user with manager role
  * Post /entity/{realm}/User for AUTO3N and body with role Shop.Manager and Integration.User

### CHANGE USER PASSWORD
* 6 Change-self password

### RESET USER PASSWORD
* 7 Reset password for manager user
## Negative

