package com.google.javascript.jscomp;

import java.net.URI;

/**
 * Created by jaen on 13/09/15.
 */
public interface IJavascriptModuleLoader {
    /**
     * Find a CommonJS module {@code requireName} relative to {@code context}.
     * @return The normalized module URI, or {@code null} if not found.
     */
    URI locateCommonJsModule(String requireName, CompilerInput context);

    /**
     * Find an ES6 module {@code moduleName} relative to {@code context}.
     * @return The normalized module URI, or {@code null} if not found.
     */
    URI locateEs6Module(String moduleName, CompilerInput context);

    /**
     * Normalizes the address of {@code input} and resolves it against the module roots.
     */
    URI normalizeInputAddress(CompilerInput input);

    /**
     * Turns a filename into a JS identifier that is used for moduleNames in
     * rewritten code. Removes leading ./, replaces / with $, removes trailing .js
     * and replaces - with _. All moduleNames get a "module$" prefix.
     */
    String toModuleName(URI filename);

    /**
     * Turns a filename into a JS identifier that is used for moduleNames in
     * rewritten code. Removes leading ./, replaces / with $, removes trailing .js
     * and replaces - with _. All moduleNames get a "module$" prefix.
     */
    String toModuleIdentifier(URI filename);
}
