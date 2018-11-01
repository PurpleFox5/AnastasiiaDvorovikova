Feature: Different Elements tests

  Scenario: Home Page Interface Test
    Given I'm on the Home Page
    Then The title is Home Page
    When I login as user epam with password 1234
    Then The user name is PITER CHAILOVSKII

    When I click on Service subcategory in the header
    Then The drop down contains options
    When I click on Service subcategory in the left section
    Then The that drop down in the left section contains options
    When I open through the header menu Service -> Different Elements Page
    Then The title is Different Elements



