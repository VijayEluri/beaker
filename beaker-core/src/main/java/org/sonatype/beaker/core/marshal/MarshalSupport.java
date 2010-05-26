package org.sonatype.beaker.core.marshal;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.beaker.core.Meep;
import org.sonatype.beaker.core.lexicon.Fault;
import org.sonatype.beaker.core.lexicon.Generic;
import org.sonatype.beaker.core.lexicon.GroupPop;
import org.sonatype.beaker.core.lexicon.GroupPush;
import org.sonatype.beaker.core.lexicon.Header;
import org.sonatype.beaker.core.lexicon.Message;
import org.sonatype.beaker.core.lexicon.StackTrace;
import org.sonatype.beaker.core.lexicon.Summary;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public abstract class MarshalSupport
{
    public static final String MEEP_STREAM = "meep-stream";

    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected final XStream xstream = new XStream(new XppDriver());

    protected final Set<Class> registered = new HashSet<Class>();

    public MarshalSupport() {
        // Register the core types
        register(
            Meep.class,
            Fault.class,
            Generic.class,
            GroupPop.class,
            GroupPush.class,
            Header.class,
            Message.class,
            StackTrace.class,
            Summary.class,
            TypeDef.class
        );
    }

    public Set<Class> getRegistered() {
        return registered;
    }

    public boolean register(final Class type) {
        // Skip if already registered
        if (registered.contains(type)) {
            return false;
        }

        log.debug("Registering type: {}", type);
        xstream.processAnnotations(type);
        registered.add(type);
        return true;
    }

    public void register(final Class... classes) {
        assert classes != null;
        for (Class type : classes) {
            register(type);
        }
    }

    protected void marshal(final Meep meep, final ObjectOutputStream output) throws IOException {
        assert output != null;

        final Class type = meep.getDetailType();
        final boolean register = register(type);

        synchronized (output) {
            if (register) {
                output.writeObject(new TypeDef(type));
            }
            output.writeObject(meep);
            output.flush();
        }
    }

    protected Meep unmarshal(final ObjectInputStream input) throws ClassNotFoundException, IOException {
        assert input != null;

        Object obj;
        synchronized (input) {
            obj = input.readObject();
            if (obj instanceof TypeDef) {
                TypeDef def = (TypeDef)obj;
                register(def.getType());
                obj = input.readObject();
            }
        }

        return (Meep)obj;
    }
}