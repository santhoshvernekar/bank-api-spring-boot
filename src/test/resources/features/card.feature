Feature: Card Functionality

  @sanity
  Scenario: Withdraw Money using DEBIT_CARD
    Given Card with CardId $1 exist in System
    And Check Balance for Account with CardId $1 in System
    When I withdraw $600 from Account with CardId $1
    And Check Balance for Account with CardId $1 in System
    Then Transactions should be in Audits for Account with CardId $1

  @sanity
  Scenario: Withdraw Excess Money using DEBIT_CARD
    Given Card with CardId $1 exist in System
    And Check Balance for Account with CardId $1 in System
    When I withdraw $300 from Account with CardId $1
    And I withdraw $1000 from Account with CardId $1
    And Check Balance for Account with CardId $1 in System
    Then Transactions should be in Audits for Account with CardId $1

  @sanity
  Scenario: Withdraw Money using CREDIT_CARD
    Given Card with CardId $2 exist in System
    And Check Balance for Account with CardId $2 in System
    When I withdraw $150 from Account with CardId $2
    And Check Balance for Account with CardId $2 in System
    Then Transactions should be in Audits for Account with CardId $2

  @sanity
  Scenario: Withdraw Excess Money using CREDIT_CARD
    Given Card with CardId $2 exist in System
    And Check Balance for Account with CardId $2 in System
    When I withdraw $200 from Account with CardId $2
    And I withdraw $1000 from Account with CardId $2
    And Check Balance for Account with CardId $2 in System
    Then Transactions should be in Audits for Account with CardId $2


  @sanity
  Scenario: Transfer Money from CREDIT_CARD to Other
    Given Card with CardId $2 exist in System
    And Card with CardId $1 exist in System
    When I transfer $100 from First Account with CardId $2 to Account with CardId $1
    And Check Balance for Account with CardId $2 in System
    When I transfer $200 from First Account with CardId $2 to Account with CardId $1
    And Check Balance for Account with CardId $2 in System
    And Check Balance for Account with CardId $1 in System


  @sanity
  Scenario: Transfer Money from DEBIT_CARD to Other
    Given Card with CardId $1 exist in System
    And Card with CardId $2 exist in System
    When I transfer $200 from First Account with CardId $1 to Account with CardId $2
    And Check Balance for Account with CardId $1 in System
    When I transfer $150 from First Account with CardId $1 to Account with CardId $2
    And Check Balance for Account with CardId $2 in System
    And Check Balance for Account with CardId $1 in System