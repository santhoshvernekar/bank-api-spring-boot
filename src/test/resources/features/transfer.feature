Feature: Transfer Functionality

  @sanity
  Scenario: Transfer Money from DEBIT_CARD Account to Other Account
    Given Account with AccountId $1 exist in System
    And Account with AccountId $2 exist in System
    When I transfer $100 from First Account with AccountId $1 to Account with AccountId $2
    And Check Balance for Account with AccountId $1 in System
    Then Check Balance for Account with AccountId $2 in System
    And Transactions should be in Audits for Account with AccountId $1
    Then Transactions should be in Audits for Account with AccountId $2

  @sanity
  Scenario: Transfer Money from One Account to Other when balance is Zero
    Given Account with AccountId $3 exist in System
    And Account with AccountId $2 exist in System
    When I transfer $100 from First Account with AccountId $3 to Account with AccountId $2

  @sanity
  Scenario: Transfer Money from CREDIT_CARD Account to  Account
    Given Account with AccountId $2 exist in System
    And Account with AccountId $1 exist in System
    And Check Balance for Account with AccountId $1 in System
    Then Check Balance for Account with AccountId $2 in System
    When I transfer $150 from First Account with AccountId $2 to Account with AccountId $1
    And I transfer $100 from First Account with AccountId $2 to Account with AccountId $1
    And I transfer $200 from First Account with AccountId $2 to Account with AccountId $1
    Then Check Balance for Account with AccountId $1 in System
    And Transactions should be in Audits for Account with AccountId $1
    And Check Balance for Account with AccountId $2 in System
    Then Transactions should be in Audits for Account with AccountId $2

