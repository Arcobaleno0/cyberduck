package ch.cyberduck.ui.cocoa;

/*
 *  Copyright (c) 2003 David Kocher. All rights reserved.
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

import ch.cyberduck.core.Host;
import ch.cyberduck.core.Session;
import com.apple.cocoa.application.*;
import com.apple.cocoa.foundation.*;
import org.apache.log4j.Logger;

/**
* @version $Id$
 */
public class CDFavoriteController {
    private static Logger log = Logger.getLogger(CDFavoriteController.class);
    
    private static final String FTP_STRING = NSBundle.localizedString("FTP (File Transfer)");
    private static final String SFTP_STRING = NSBundle.localizedString("SFTP (SSH Secure File Transfer)");

    private Host host;
    
    // ----------------------------------------------------------
    // Outlets
    // ----------------------------------------------------------
    
    private NSWindow window;
    public void setWindow(NSWindow window) {
	this.window = window;
    }
    
    private NSPopUpButton protocolPopup;
    public void setProtocolPopup(NSPopUpButton protocolPopup) {
	this.protocolPopup = protocolPopup;
	this.protocolPopup.setTarget(this);
	this.protocolPopup.setAction(new NSSelector("protocolSelectionChanged", new Class[] {Object.class}));
    }
    private NSTextField nicknameField; // IBOutlet
    public void setNicknameField(NSTextField nicknameField) {
	this.nicknameField = nicknameField;
    }
    private NSTextField hostField; // IBOutlet
    public void setHostField(NSTextField hostField) {
	this.hostField = hostField;
    }
    private NSTextField urlField; // IBOutlet
    public void setUrlField(NSTextField urlField) {
	this.urlField = urlField;
    }
    private NSTextField usernameField; // IBOutlet
    public void setUsernameField(NSTextField usernameField) {
	this.usernameField = usernameField;
    }

    private static NSMutableArray allDocuments = new NSMutableArray();
    
    // ----------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------
    
    public CDFavoriteController(Host favorite) {
	log.debug("CDFavoriteController:"+favorite);
	this.host = favorite;
	allDocuments.addObject(this);
        if (false == NSApplication.loadNibNamed("Favorite", this)) {
            log.fatal("Couldn't load Favorite.nib");
            return;
        }
    }
    
    public void awakeFromNib() {
	log.debug("awakeFromNib");
	NSPoint origin = this.window.frame().origin();
	this.window.setFrameOrigin(new NSPoint(origin.x() + 16, origin.y() - 16));
	this.window.setTitle(this.host.getNickname());
	(NSNotificationCenter.defaultCenter()).addObserver(
						    this,
						    new NSSelector("hostInputDidEndEditing", new Class[]{NSNotification.class}),
						    NSControl.ControlTextDidEndEditingNotification,
						    hostField);
	(NSNotificationCenter.defaultCenter()).addObserver(
						    this,
						    new NSSelector("nicknameInputDidEndEditing", new Class[]{NSNotification.class}),
						    NSControl.ControlTextDidEndEditingNotification,
						    nicknameField);
	(NSNotificationCenter.defaultCenter()).addObserver(
						    this,
						    new NSSelector("usernameInputDidEndEditing", new Class[]{NSNotification.class}),
						    NSControl.ControlTextDidEndEditingNotification,
						    usernameField);
	this.updateFields();
    }
    
    public void windowWillClose(NSNotification notification) {
	this.window().setDelegate(null);
	NSNotificationCenter.defaultCenter().removeObserver(this);
	allDocuments.removeObject(this);
    }
    
    public void protocolSelectionChanged(Object sender) {
	log.debug("protocolSelectionChanged:"+sender);
	int tag = protocolPopup.selectedItem().tag();
	switch(tag) {
	    case(Session.SSH_PORT):
		this.host.setProtocol(Session.SFTP);
		this.host.setPort(Session.SSH_PORT);
		break;
	    case(Session.FTP_PORT):
		this.host.setProtocol(Session.FTP);
		this.host.setPort(Session.FTP_PORT);
		break;
	}
	this.updateFields();
    }
    
    public void hostInputDidEndEditing(NSNotification sender) {
	log.debug("hostInputDidEndEditing");
	this.host.setHostname(hostField.stringValue());
	this.updateFields();
    }

    public void nicknameInputDidEndEditing(NSNotification sender) {
	log.debug("nicknameInputDidEndEditing");
	this.host.setNickname(nicknameField.stringValue());
	this.updateFields();
    }

    public void usernameInputDidEndEditing(NSNotification sender) {
	log.debug("usernameInputDidEndEditing");
	this.host.getLogin().setUsername(usernameField.stringValue());
	this.updateFields();
    }
    
    private void updateFields() {
	this.window.setTitle(this.host.getNickname());
	this.urlField.setStringValue(this.host.getURL());
	this.nicknameField.setStringValue(this.host.getNickname());
	this.usernameField.setStringValue(this.host.getLogin().getUsername());
	this.protocolPopup.selectItemWithTitle(this.host.getProtocol().equals(Session.FTP) ? FTP_STRING : SFTP_STRING);
    }
    
    public NSWindow window() {
	return this.window;
    }
}
