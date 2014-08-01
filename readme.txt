

    UI 
    ------------------------------------------------------------------
    * uses Wicket Model's to display data
    * introduced user-count label and add-users button
    * button click handler introduced

    Domain
    ------------------------------------------------------------------
    We already had AllUsersQuery.
    * introduced SaveUserCommand 

    DAO
    ------------------------------------------------------------------
    We already had AllUsersQueryDaoMem.
    * introduced SaveUserCommandDaoMem
    * introduced SaveUserCommandDaoMemTest
    * introduced GetUserByIdQueryDaoMem used only in SaveUserCommandDaoMemTest
    
