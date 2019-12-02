# openpojo-ext

An extension to [openpojo](https://github.com/OpenPojo/openpojo), providing additional assertions. Made available here to prevent copy/paste across projects.

## Deploying to Maven Local

```
gradlew clean build publishToMavenLocal
```

## Accessing in another project

1. Ensure that your project is pulling from `mavenLocal`

```
    repositories {
        mavenLocal()
        ...
    }
```

2. Add the dependency to your project's `build.gradle`.

```
testCompile "com.atttomyx:openpojo-ext:0.0.1"
```

3. Add a `TestDtos` test for your DTOs.

```
// see https://github.com/OpenPojo/openpojo
public class TestDtos {

    private static final String POJO_PACKAGE = "com.atttomyx.example.api.v1.dto";

    @Test
    public void testPojoStructureAndBehavior() {
        Validator validator = ValidatorBuilder.create()
                .with(new NoPublicFieldsRule())
                .with(new NoPrimitivesRule())
                .with(new GetterMustExistRule())
                .with(new SetterMustExistRule())
                .with(new EqualsMustExistRule())
                .with(new HashCodeMustExistRule())
                .with(new ToStringMustExistRule())
                .with(new GetterTester())
                .with(new SetterTester())
                .with(new EqualsTester())
                .with(new HashCodeTester())
                .with(new ToStringTester())
                .build();

        validator.validate(POJO_PACKAGE, new FilterPackageInfo(), new ExcludeTestsFilter());
    }
}
```

## Owner

[Mike Daniels](mailto:mikedaniels1546@gmail.com)
