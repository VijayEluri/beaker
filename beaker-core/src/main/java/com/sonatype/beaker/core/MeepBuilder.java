package com.sonatype.beaker.core;

import com.sonatype.beaker.lexicon.Fault;
import com.sonatype.beaker.lexicon.Meep;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class MeepBuilder
{
    private static final Logger log = LoggerFactory.getLogger(MeepBuilder.class);

    // HACK: Fixme...

    public static void meep(final Meep meep, final Object source) {
        assert meep != null;
        assert source != null;

        try {
            PropertyUtils.copyProperties(meep, source);
            Beaker.meep(meep);
        }
        catch (Exception e) {
            Beaker.meep(new Fault(e));
        }
    }
}