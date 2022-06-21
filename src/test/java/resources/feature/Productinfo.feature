Feature: Testing different products on the Best Api Application

  Scenario: Check if the GoRest application is accessed by the users
    Given I am on Homepage of application GoRest
    When User send a GET Request to list endpoint products
    Then User can get back a valid status code 200 of product

  Scenario: Check if User can add  products in the application
    Given I am on Homepage of application GoRest
    When User can create new product using POST method in products application
    Then User can get back a valid status code 201 of product

  Scenario: Check if User can update  products in the application
    Given I am on Homepage of application GoRest
    When User can create new product using PUT method in products application
    Then User can get back a valid status code 200 of product

  Scenario: Check if User can Delete  products in the application
    Given I am on Homepage of application GoRest
    When User can Delete new product using DELETE method in products application
    Then User can get back a valid status code 200 of product
    And User verify that the product is deleted successfully from product

    Scenario: Verify the data from the given response
      Given I am on Homepage of application GoRest
      When User send a GET Request to list endpoint users
      Then I validate Total records are "20"
      And  I validate id 2492 has name "Fr. Gatik Ahluwalia"
      And  I validate id 2483 has email "birjesh_acharya@brown.net"
      And  I validate all id has status "active"
      And  I validate id 2472 has gender "female"
      And  I validate id 2471 has gender "male"