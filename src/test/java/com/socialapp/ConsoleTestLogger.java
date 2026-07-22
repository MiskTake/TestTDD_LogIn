package com.socialapp;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class ConsoleTestLogger implements TestWatcher {

    private static final String SEPARATOR = "--------------------------------------------------";
    private static Class<?> lastTestClass = null;

    @Override
    public void testSuccessful(ExtensionContext context) {
        printSeparatorIfNewClass(context);
        System.out.println("[PASS] " + context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        printSeparatorIfNewClass(context);
        System.out.println("[FAIL] " + context.getDisplayName());
    }

    private void printSeparatorIfNewClass(ExtensionContext context) {
        Class<?> currentTestClass = context.getRequiredTestClass();
        if (!currentTestClass.equals(lastTestClass)) {
            String className = context.getParent()
                .map(ExtensionContext::getDisplayName)
                .orElse(currentTestClass.getSimpleName());

            System.out.println();
            System.out.println(SEPARATOR);
            System.out.println(className);
            System.out.println(SEPARATOR);
            lastTestClass = currentTestClass;
        }
    }
}
