package com.sonatype.beaker.core.marshal;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * ???
 *
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
@XStreamAlias("type-def")
public class TypeDef
{
    @XStreamAsAttribute
    private final Class type;

    public TypeDef(final Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TypeDef{" +
            "type=" + type +
            '}';
    }
}