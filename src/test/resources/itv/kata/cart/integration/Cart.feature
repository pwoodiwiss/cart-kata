Feature: Cart
  Scenario: New cart should calculate 0;
    When new cart
    Then calculateTotalCost should return 0

    Scenario: Should handle basic cart without offers;
    Given The following pricing
    | sku | price | offer |
    | A   | 50    |       |
    | B   | 30    |       |
    When We have cart with
    | sku | quantity |
    | A   |  2       |
    | B   |  4       |
    Then calculateTotalCost should return 220

  Scenario: Cart should handle complex pricing and offers;
    Given The following pricing
    | sku | price | offer     |
    | A   | 50    | 3 for 130 |
    | B   | 30    | 2 for 45  |
    | C   | 20    |           |
    | D   | 15    |           |
    When We have cart with
    | sku | quantity |
    | A   |  4       |
    | B   |  4       |
    | C   |  2       |
    | D   |  1       |
    Then calculateTotalCost should return 325