package com.sonatype.beaker.core;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public interface Handler
{
    void handle(Meep meep) throws Exception;

    void stop() throws Exception;
}