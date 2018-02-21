import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class AdvancedCalculator {

    public static void main(String[] args) throws IOException {
        List<BuiltInFunctionInterface> functions = new ArrayList<>();
        BuiltInFunctionInterface reverse = new ReverseFunction();
        functions.add(reverse);
        runProgram("program1.txt",System.out, functions);
    }

    public Object startProgram(CharStream input, OutputStream stream, List<BuiltInFunctionInterface> functions){


        final AdvancedCalculatorLexer lexer = new AdvancedCalculatorLexer(input);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final AdvancedCalculatorParser parser = new AdvancedCalculatorParser(tokens);

        final ParseTree tree = parser.program();
        final AdvancedCalculatorVisitor<Object> visitor = new AdvancedCalculatorVisitorImpl(stream, functions);
        return visitor.visit(tree);
    }

    private static Object runProgram(String filename, OutputStream stream, List<BuiltInFunctionInterface> functions) throws IOException {
        final CharStream input = CharStreams.fromFileName(filename);
        final AdvancedCalculatorLexer lexer = new AdvancedCalculatorLexer(input);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final AdvancedCalculatorParser parser = new AdvancedCalculatorParser(tokens);

        final ParseTree tree = parser.program();
        final AdvancedCalculatorVisitor<Object> visitor = new AdvancedCalculatorVisitorImpl(stream, functions);
        return visitor.visit(tree);
    }
}
