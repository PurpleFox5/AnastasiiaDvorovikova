Feature: Different Elements tests

  Scenario: Home Page Interface Test
    Given I'm on the Home Page
    Then The browser title is Home Page
    When I login as user epam with password 1234
    Then The user name is PITER CHAILOVSKII

    Then There are 4 pictures
    Then There are 4 texts under pictures
    Then There are 2 texts above pictures

    When I click on Service subcategory in the header
    Then The drop down contains options
    When I click on Service subcategory in the left section
    Then The that drop down in the left section contains options

    When I click on Service subcategory in the header
    When I open through the header menu Service -> Different Elements Page

  Scenario: Different Elements Interface Test
    Then The title is Different Elements

    Then There are 4 checkboxes
    And There are 4 radioButtons
    And There is 1 dropdown
    And There are 2 buttons

    Then There is Right Section
    Then There is Left Section

    When I select checkboxes WATER, WIND
    Then Checkboxes WATER, WIND are checked
    Then Check logs WATER: condition changed to true, WIND: condition changed to true

    When I select radio SELEN
    Then Radiobutton SELEN is selected
    Then Check logs metal: value changed to SELEN

    When I select in dropdown Yellow
    Then Dropdown Yellow is selected
    Then Check logs Colors: value changed to Yellow

    When I unselect checkboxes WATER, WIND
    Then Checkboxes WATER, WIND are unchecked
    Then Check logs WATER: condition changed to false, WIND: condition changed to false