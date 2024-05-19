Feature: Purchase order execution from eCOmmerce website

  Background: 
    Given I am on the eCommerce login page

  @positive
  Scenario Outline: Positive test for completing the order purchase
    Given Logged in with the <userName> and <password>
    When I add the product <productName> to cart
    And submit the order for <productName>
    Then I verify "THANK YOU FOR THE ORDER." is displayed

    Examples: 
      | userName          | password    | productName     |
      | anshika@gmail.com | IamKing@000 | ZARA COAT 3     |
      | shetty@gmail.com  |    12345678 | ADIDAS ORIGINAL |
