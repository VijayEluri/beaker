package com.sonatype.beaker.lexicon;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public abstract class MeepSupport
    implements Meep
{
    private Context context;

    public Context getContext() {
        if (context == null) {
            context = new Context();
        }
        return context;
    }
}