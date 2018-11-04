//package configurations;
//
//import cucumber.api.TypeRegistry;
//import cucumber.api.TypeRegistryConfigurer;
//import entries.User;
//import enums.Users;
//import io.cucumber.cucumberexpressions.ParameterType;
//import io.cucumber.cucumberexpressions.Transformer;
//import io.cucumber.datatable.DataTableType;
//import io.cucumber.datatable.TableEntryTransformer;
//
//import java.util.Locale;
//import java.util.Map;
//
//import static java.util.Locale.ENGLISH;
//
//public class DataTableConfigurer  implements TypeRegistryConfigurer {
//
//    @Override
//    public Locale locale() {
//        return ENGLISH;
//    }
//
//    @Override
//    public void configureTypeRegistry(TypeRegistry typeRegistry) {
//        typeRegistry.defineParameterType(new ParameterType<Users>(
//                "user", "[\\w]+", Users.class, (Transformer<Users>) Users::valueOf)
//        );
//        typeRegistry.defineParameterType(new ParameterType<Integer>(
//                "digit",
//                "[0-9]",
//                Integer.class,
//                (Transformer<Integer>) Integer::parseInt)
//        );
//        typeRegistry.defineDataTableType(new DataTableType(User.class,
//                (TableEntryTransformer<User>) entry -> new User(entry.get("number"), entry.get("user"), entry.get("description"))));
//
//    }
//}
