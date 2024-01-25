package cotuba;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;

@AnalyzeClasses(packages = "cotuba")
class ModularyTest {

    @ArchTest
    static final ArchRule no_cycles = SlicesRuleDefinition.slices().matching("cotuba.(*)..").should().beFreeOfCycles();

}
