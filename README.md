# Milesoft OpenPojo Extensions (`openpojo-ext`)

An advanced, reflection-driven test utility library that extends the [OpenPojo](https://github.com/OpenPojo/openpojo) framework, providing robust custom assertions and behavior validations for Domain Transfer Objects (DTOs) and POJOs.

---

## Prerequisites & Repository Access

For secure repository access and setting up your local environment, please see our [Developer Onboarding Guide](https://milesoft.io/docs/onboarding).

## Consuming the Library

### 1. Apply the Plugin and Add the Repository
To resolve the `artifactregistry://` transport protocol, apply the Google Cloud Artifact Registry plugin and add our secure repository to your `build.gradle`:

```groovy
plugins {
    id "com.google.cloud.artifactregistry.gradle-plugin" version "2.2.5"
}

repositories {
    mavenCentral()
    maven { url "artifactregistry://us-west2-maven.pkg.dev/milesoft-repo/repo-maven" }
}
```

### 2. Add the Dependency
```groovy
dependencies {
    testImplementation "io.milesoft:openpojo-ext:1.0.0"
}
```

### 3. Quickstart Code Example

Create a standard test class (e.g., `TestDtos.java`) inside your project's test package to automatically scan, validate, and verify that 100% of your DTO structures, getters, setters, equals, hashcodes, and toString implementations are correct and complete:

```java
import com.openpojo.reflection.filters.ExcludeTestsFilter;
import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.EqualsMustExistRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.HashCodeMustExistRule;
import com.openpojo.validation.rule.impl.NoPrimitivesRule;
import com.openpojo.validation.rule.impl.NoPublicFieldsRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.rule.impl.ToStringMustExistRule;
import com.openpojo.validation.test.impl.EqualsTester;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.HashCodeTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.openpojo.validation.test.impl.ToStringTester;
import org.junit.Test;

public class TestDtos {

    // Target the package containing your DTOs/POJOs
    private static final String POJO_PACKAGE = "io.milesoft.example.api.v1.dto";

    @Test
    public void testPojoStructureAndBehavior() {
        Validator validator = ValidatorBuilder.create()
            // Enforce structural rules
            .with(new NoPublicFieldsRule())
            .with(new NoPrimitivesRule()) // Enforce objects instead of primitives for clean deserialization
            .with(new GetterMustExistRule())
            .with(new SetterMustExistRule())
            .with(new EqualsMustExistRule())
            .with(new HashCodeMustExistRule())
            .with(new ToStringMustExistRule())
            
            // Execute behavioral testers
            .with(new GetterTester())
            .with(new SetterTester())
            .with(new EqualsTester())
            .with(new HashCodeTester())
            .with(new ToStringTester())
            .build();

        // Validate the entire package at once, ignoring test suites themselves
        validator.validate(POJO_PACKAGE, new FilterPackageInfo(), new ExcludeTestsFilter());
    }
}
```

---

## Contributing & Internal Development (Milesoft Team Only)

This section is for Milesoft developers contributing to or maintaining the `openpojo-ext` library.

### Local Development & Testing
To compile the library and execute 100% of all integrated test cases:
```bash
./gradlew clean test
```

### Publishing Locally
To compile, package, and publish the library to your local Maven cache (`~/.m2/repository`):
```bash
./gradlew publishToMavenLocal
```

### Deploying to production Artifact Registry
To publish a release build directly to our secure, client-facing GCP Artifact Registry:
1. Ensure you are authenticated with the Google Cloud CLI (`gcloud auth login`) and have the necessary IAM permissions to write to the registry.
2. Increment the version inside `build.gradle`.
3. Execute the publish task:
   ```bash
   ./gradlew publish
   ```
