package ch.cyberduck.ui.cocoa;

/*
 *  Copyright (c) 2005 David Kocher. All rights reserved.
 *  http://cyberduck.ch/
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  Bug fixes, suggestions and comments should be sent to:
 *  dkocher@cyberduck.ch
 */

import com.apple.cocoa.application.NSView;
import com.apple.cocoa.foundation.NSCoder;
import com.apple.cocoa.foundation.NSRect;

/**
 * @version $Id$
 */
public class CDProgressCell extends CDTableCell {

	public CDProgressCell() {
		super();
	}

	protected CDProgressCell(NSCoder decoder, long token) {
		super(decoder, token);
	}

	protected void encodeWithCoder(NSCoder encoder) {
		super.encodeWithCoder(encoder);
	}

	private NSView subview;

	public void setObjectValue(Object subview) {
		this.subview = (NSView)subview;
	}

	public void drawInteriorWithFrameInView(NSRect cellFrame, NSView controlView) {
		if(this.subview != null) {
			this.subview.setFrame(cellFrame);
			if(this.subview.superview() != controlView) {
				controlView.addSubview(this.subview);
			}
		}
	}
}