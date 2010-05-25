package com.sonatype.beaker.core.handler;

import com.sonatype.beaker.core.Meep;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public interface Handler
{
    void open() throws Exception;
    
    void handle(Meep meep) throws Exception;

    void close() throws Exception;
}