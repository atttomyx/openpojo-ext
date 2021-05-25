# openpojo-ext

An extension to [openpojo](https://github.com/OpenPojo/openpojo), providing additional assertions. Made available here to prevent copy/paste across projects.

## Accessing in another project

1. Add the `jitpack` repository to your `build.gradle`
   
```
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
```

2. Add the dependency to your `build.gradle`

```
    dependencies {
        testCompile 'com.github.atttomyx:openpojo-ext:{version}'
    }
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
