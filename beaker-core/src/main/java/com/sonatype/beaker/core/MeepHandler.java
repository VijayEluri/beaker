package com.sonatype.beaker.core;

import com.sonatype.beaker.lexicon.Meep;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public interface MeepHandler
{
    void handle(Meep meep) throws Exception;

    void stop() throws Exception;
}