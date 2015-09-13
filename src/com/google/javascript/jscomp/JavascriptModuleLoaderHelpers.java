package com.google.javascript.jscomp;

import java.net.URI;
import java.util.regex.Pattern;

/**
 * Created by jaen on 13/09/15.
 */
public class JavascriptModuleLoaderHelpers {
    /** According to the spec, the forward slash should be the delimiter on all platforms. */
    static final String MODULE_SLASH = "/";
    /** The default module root, the current directory. */
    public static final String DEFAULT_FILENAME_PREFIX = "." + MODULE_SLASH;

    static final DiagnosticType LOAD_ERROR = DiagnosticType.error(
            "JSC_ES6_MODULE_LOAD_ERROR",
            "Failed to load module \"{0}\"");

    public static String stripJsExtension(String fileName) {
        if (fileName.endsWith(".js")) {
            return fileName.substring(0, fileName.length() - ".js".length());
        }
        return fileName;
    }

    /** Whether this is relative to the current file, or a top-level identifier. */
    public static boolean isRelativeIdentifier(String name) {
        return name.startsWith("." + MODULE_SLASH) || name.startsWith(".." + MODULE_SLASH);
    }

    /**
     * Turns a filename into a JS identifier that is used for moduleNames in
     * rewritten code. Removes leading ./, replaces / with $, removes trailing .js
     * and replaces - with _. All moduleNames get a "module$" prefix.
     */
    public static String toModuleName(URI filename) {
        String moduleName =
                stripJsExtension(filename.toString())
                        .replaceAll("^\\." + Pattern.quote(MODULE_SLASH), "")
                        .replace(MODULE_SLASH, "$")
                        .replace('\\', '$')
                        .replace('-', '_')
                        .replace(':', '_')
                        .replace('.', '_');
        return "module$" + moduleName;
    }
}
