package org.sonatype.beaker.core.handler;

import org.sonatype.beaker.core.Meep;

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