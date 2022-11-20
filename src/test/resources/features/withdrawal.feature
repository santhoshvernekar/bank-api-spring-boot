Feature: Withdrawal Functionality

  @sanity
  Scenario: Withdraw Money from Account
    Given Account with AccountId $1 exist in System
    When I withdraw $100 from Account with AccountId $1
    And Check Balance for Account with AccountId $1 in System
    Then Transactions should be in Audits for Account with AccountId $1

  @sanity
  Scenario: Withdraw Excess Money from Account
    Given Account with AccountId $1 exist in System
    And Check Balance for Account with AccountId $1 in System
    And I withdraw $200 from Account with AccountId $1
    When Check Balance for Account with AccountId $1 in System
    Then I withdraw $1000 from Account with AccountId $1
