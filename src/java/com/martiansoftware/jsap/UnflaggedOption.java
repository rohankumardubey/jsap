/*
 * Copyright (c) 2002-2004, Martian Software, Inc.
 * This file is made available under the LGPL as described in the accompanying
 * LICENSE.TXT file.
 */

package com.martiansoftware.jsap;

/**
 * An option whose meaning is derived from its <i>position in the argument
 * list</i> rather than a flag
 * that precedes it.  UnflaggedOptions allow the parsing of command lines
 * without flags, such as
 * "compressfiles destinationFile file1 file2 file3 file4 ...", where
 * "destinationFile" is the name of the
 * file to create, and file1 through file 4 (and beyond) are the names of the
 * files to compress.  The JSAP
 * that supports this command line has only two options defined: the first
 * accepts a single destination file,
 * and the second is "greedy," consuming the remaining unflagged options.
 * @author <a href="http://www.martiansoftware.com/contact.html">Marty Lamb</a>
 * @see com.martiansoftware.jsap.FlaggedOption
 * @see com.martiansoftware.jsap.Option
 */
public final class UnflaggedOption extends Option {

    /**
     * Boolean indicating whether or not this UnflaggedOption is greedy.
     * Default is JSAP.NOT_GREEDY.
     */
    private boolean greedy = JSAP.NOT_GREEDY;

    /**
     * Creates a new UnflaggedOption with the specified unique ID.
     * @param id the unique ID for this UnflaggedOption.
     */
    public UnflaggedOption(String id) {
        super(id);
    }

    /**
     * A shortcut constructor that creates a new UnflaggedOption and configures
     * its most commonly used settings.
     * @param id the unique ID for this UnflaggedOption
     * @param stringParser the StringParser this UnflaggedOption should use.
     * @param defaultValue the default value for this UnflaggedOption (may be
     * null).
     * @param required if true, this UnflaggedOption is required.
     * @param greedy if true, this UnflaggedOption is greedy.
     * */
    public UnflaggedOption(
        String id,
        StringParser stringParser,
        String defaultValue,
        boolean required,
        boolean greedy) {
        this(id);
        setStringParser(stringParser);
        setDefault(defaultValue);
        setRequired(required);
        setGreedy(greedy);
    }

    /**
     * Sets whether this UnflaggedOption is greedy.  A greedy UnflaggedOption
     * consumes all the remaining
     * UnflaggedOptions from the argument list.  As a result, only one greedy
     * UnflaggedOption may be registered with
     * a JSAP, and it must be the last UnflaggedOption registered.
     * @param greedy if true, this UnflaggedOption will be greedy.
     * @return the modified UnflaggedOption
     */
    public UnflaggedOption setGreedy(boolean greedy) {
        enforceParameterLock();
        this.greedy = greedy;
        return (this);
    }

    /**
     * Returns a boolean indicating whether this UnflaggedOption is greedy.
     * @return a boolean indicating whether this UnflaggedOption is greedy.
     */
    public boolean isGreedy() {
        return (greedy);
    }

    /**
     * Returns syntax instructions for this FlaggedOption.
     * @return syntax instructions for this FlaggedOption based upon its current
     * configuration.
     */
    public String getSyntax() {
        StringBuffer result = new StringBuffer();
        if (!required()) {
            result.append("[");
        }

        String id = getID();
        if (this.isGreedy()) {
            result.append(id + "1 " + id + "2 ... " + id + "N");
        } else {
            result.append("<" + id + ">");
        }
        if (!required()) {
            result.append(" ]");
        }
        return (result.toString());
    }

    /**
     * Sets whether this UnflaggedOption is a list.  Default behavior is
     * JSAP.NOT_LIST.
     * @param isList if true, this Option is a list.
     * @return the modified UnflaggedOption
     */
    public UnflaggedOption setList(boolean isList) {
        super.internalSetList(isList);
        return (this);
    }

    /**
     * Sets the list separator character for this UnflaggedOption.  The default
     * list separator is JSAP.DEFAULT_LISTSEPARATOR.
     * @param listSeparator the list separator for this Option.
     * @return the modified UnflaggedOption
     */
    public UnflaggedOption setListSeparator(char listSeparator) {
        super.internalSetListSeparator(listSeparator);
        return (this);
    }

    /**
     * Sets whether this UnflaggedOption is required.  Default is
     * JSAP.NOT_REQUIRED.
     * @param required if true, this Option will be required.
     * @return the modified UnflaggedOption
     */
    public UnflaggedOption setRequired(boolean required) {
        super.internalSetRequired(required);
        return (this);
    }

    /**
     * Sets the StringParser to which this UnflaggedOption's parse() method
     * should delegate.
     * @param stringParser the StringParser to which this Option's parse()
     * method should delegate.
     * @see com.martiansoftware.jsap.StringParser
     * @return the modified UnflaggedOption
     */
    public UnflaggedOption setStringParser(StringParser stringParser) {
        super.internalSetStringParser(stringParser);
        return (this);
    }
}
