package com.sonatype.beaker.tools;

import com.sonatype.beaker.core.Meep;
import com.sonatype.beaker.core.lexicon.GroupPop;
import com.sonatype.beaker.core.lexicon.GroupPush;
import com.sonatype.beaker.core.marshal.Unmarshaller;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Stack;
import java.util.zip.GZIPInputStream;

/**
 * ???
 * 
 * @author <a href="mailto:jason@planet57.com">Jason Dillon</a>
 * @since 0.1
 */
public class Main
{
    public static void main(final String[] args) throws Exception {
        File file = new File(args[0]);

        InputStream stream;
        if (file.getName().endsWith(".gz")) {
            stream = new GZIPInputStream(new FileInputStream(file));
        }
        else {
            stream = new BufferedInputStream(new FileInputStream(file));
        }

        Unmarshaller marshaller = new Unmarshaller(stream);
        Stack<Long> group = new Stack<Long>();

        try {
            while (true) {
                Meep meep = marshaller.unmarshal();
                Object detail = meep.getDetail();

                StringBuilder buff = new StringBuilder();
                for (long id : group) {
                    buff.append("  ");
                }
                
                if (detail instanceof GroupPush) {
                    GroupPush push = (GroupPush)detail;
                    group.push(push.getId());
                    buff.append("(").append(push.getId());
                    if (push.getName() != null) {
                        buff.append("=").append(push.getName());
                    }
                    buff.append(") {");
                }
                else if (detail instanceof GroupPop) {
                    group.pop();
                    buff.setLength(buff.length() - 2);
                    buff.append("}");
                }
                else {
                    buff.append(detail);
                }

                System.out.println(buff);
            }
        }
        catch (EOFException e) {
            // expected
        }
    }
}
