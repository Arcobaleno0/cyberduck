package ch.cyberduck.core.transfer.upload;

import ch.cyberduck.core.AbstractTestCase;
import ch.cyberduck.core.Host;
import ch.cyberduck.core.NullLocal;
import ch.cyberduck.core.NullPath;
import ch.cyberduck.core.NullSession;
import ch.cyberduck.core.Path;
import ch.cyberduck.core.local.Local;
import ch.cyberduck.core.transfer.symlink.NullSymlinkResolver;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version $Id$
 */
public class SkipFilterTest extends AbstractTestCase {

    @Test
    public void testAccept() throws Exception {
        SkipFilter f = new SkipFilter(new NullSymlinkResolver());
        assertTrue(f.accept(new NullSession(new Host("h")), new NullPath("a", Path.FILE_TYPE) {
            @Override
            public boolean exists() {
                return false;
            }

            @Override
            public Local getLocal() {
                return new NullLocal(null, "a") {
                    @Override
                    public boolean exists() {
                        return true;
                    }
                };
            }
        }));
        assertFalse(f.accept(new NullSession(new Host("h")), new NullPath("a", Path.FILE_TYPE) {
            @Override
            public boolean exists() {
                return true;
            }

            @Override
            public Local getLocal() {
                return new NullLocal(null, "a") {
                    @Override
                    public boolean exists() {
                        return true;
                    }
                };
            }
        }));
    }
}
