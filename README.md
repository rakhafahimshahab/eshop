# Lab01
## Reflection 1
Through this tutorial, I've learned about the distinct differences between Java Spring Boot's MVC framework and Python Django's MVT architecture. MVC's clear separation between backend models and frontend views, bridged by controllers, facilitates targeted development and enhances clarity, embodying the separation of concerns principle effectively.

In reviewing my code, I see the need for uniformity in how functions access elements and the importance of securing identifiers with UUIDs to prevent unauthorized access. Additionally, this experience underscored the value of disciplined git usage, demonstrating how incremental commits and branch management can streamline development and mitigate risks. I'm motivated to further refine my git skills and explore more commands to bolster my version control practices.
## Reflection 2
1. Writing unit tests can instill a sense of confidence in the reliability and robustness of one's code. The number of unit tests within a class doesn't have a fixed rule; it should be sufficient to cover all functionalities, edge cases, and failure points of the methods within that class. To ensure comprehensive testing, one can employ code coverage tools. These tools measure the extent to which the source code is executed when the test suite runs, offering insights into any untested parts of the codebase. While achieving 100% code coverage might suggest that every line of code has been executed during testing, it doesn't guarantee the absence of bugs or errors. High code coverage can reduce the chance of bugs but cannot ensure their absence because it doesn't account for the correctness of the logic itself or the quality of the test cases. Therefore, while striving for high code coverage is beneficial, it's equally important to write meaningful and thorough test cases that validate the logic under various scenarios, not just execute all lines of code.
2. When creating another functional test suite similar to CreateProductFunctionalTest.java, especially with a focus on verifying the number of items in the product list, the cleanliness of the new code becomes a concern. Duplicating setup procedures and instance variables across multiple test classes can lead to code redundancy, making maintenance harder and potentially hiding flaws in test design. This redundancy can clutter the codebase, reduce code quality, and make future updates more error-prone. To improve cleanliness and maintainability, one could abstract common setup tasks and configurations into a shared base class or utilize test fixtures and helper methods. Employing patterns like Page Object Model (POM) can also encapsulate and reuse web interaction logic, reducing boilerplate and improving the readability and structure of test code. Additionally, leveraging dependency injection to share common test infrastructure can streamline setup and teardown processes. Implementing these strategies will not only enhance code cleanliness but also ensure that the test suite remains scalable, maintainable, and easy to understand.
# Lab02
## Reflection 1
1. there were some code quality issue, for example some function are public void and we need to remove the public argument. Also the test coverage are not 100 percent yet, so i made adjustments so that the tests covers untested functions.
2. The CI/CD workflows implemented continuous integration because they automate test execution. And it implements continuous deployment because they automate deployment process. So the project has bug detection and fast delivery of updates