package ch.cyberduck.binding.application;

/*
 * Copyright (c) 2002-2010 David Kocher. All rights reserved.
 *
 * http://cyberduck.ch/
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * Bug fixes, suggestions and comments should be sent to:
 * dkocher@cyberduck.ch
 */

import org.rococoa.ObjCClass;

/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a>, <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 *
 * @version $Id$
 */
public abstract class NSComboBoxCell extends NSTextFieldCell {
    private static final _Class CLASS = org.rococoa.Rococoa.createClass("NSComboBoxCell", _Class.class);

    public static NSComboBoxCell comboBoxCell() {
        return CLASS.alloc().init();
    }

    public interface _Class extends ObjCClass {
        NSComboBoxCell alloc();
    }

    @Override
    public abstract NSComboBoxCell init();

    /**
     * Original signature : <code>-(BOOL)hasVerticalScroller</code><br>
     * <i>native declaration : NSComboBoxCell.h:38</i>
     */
    public abstract boolean hasVerticalScroller();

    /**
     * Original signature : <code>-(void)setHasVerticalScroller:(BOOL)</code><br>
     * <i>native declaration : NSComboBoxCell.h:39</i>
     */
    public abstract void setHasVerticalScroller(boolean flag);

    /**
     * Original signature : <code>-(id)intercellSpacing</code><br>
     * <i>native declaration : NSComboBoxCell.h:40</i>
     */
    public abstract org.rococoa.cocoa.foundation.NSObject intercellSpacing();
    /**
     * <i>native declaration : NSComboBoxCell.h:41</i><br>
     * Conversion Error : /// Original signature : <code>-(void)setIntercellSpacing:()</code><br>
     * - (void)setIntercellSpacing:(null)aSize; (Argument aSize cannot be converted)
     */
    /**
     * Original signature : <code>-(CGFloat)itemHeight</code><br>
     * <i>native declaration : NSComboBoxCell.h:42</i>
     */
    public abstract org.rococoa.cocoa.CGFloat itemHeight();

    /**
     * Original signature : <code>-(void)setItemHeight:(CGFloat)</code><br>
     * <i>native declaration : NSComboBoxCell.h:43</i>
     */
    public abstract void setItemHeight(org.rococoa.cocoa.CGFloat itemHeight);

    /**
     * Original signature : <code>-(NSInteger)numberOfVisibleItems</code><br>
     * <i>native declaration : NSComboBoxCell.h:44</i>
     */
    public abstract org.rococoa.cocoa.foundation.NSInteger numberOfVisibleItems();

    /**
     * Original signature : <code>-(void)setNumberOfVisibleItems:(NSInteger)</code><br>
     * <i>native declaration : NSComboBoxCell.h:45</i>
     */
    public abstract void setNumberOfVisibleItems(org.rococoa.cocoa.foundation.NSInteger visibleItems);

    /**
     * Original signature : <code>-(void)setButtonBordered:(BOOL)</code><br>
     * <i>native declaration : NSComboBoxCell.h:48</i>
     */
    public abstract void setButtonBordered(boolean flag);

    /**
     * Original signature : <code>-(BOOL)isButtonBordered</code><br>
     * <i>native declaration : NSComboBoxCell.h:49</i>
     */
    public abstract boolean isButtonBordered();

    /**
     * Original signature : <code>-(void)reloadData</code><br>
     * <i>native declaration : NSComboBoxCell.h:52</i>
     */
    public abstract void reloadData();

    /**
     * Original signature : <code>-(void)noteNumberOfItemsChanged</code><br>
     * <i>native declaration : NSComboBoxCell.h:53</i>
     */
    public abstract void noteNumberOfItemsChanged();

    /**
     * Original signature : <code>-(void)setUsesDataSource:(BOOL)</code><br>
     * <i>native declaration : NSComboBoxCell.h:55</i>
     */
    public abstract void setUsesDataSource(boolean flag);

    /**
     * Original signature : <code>-(BOOL)usesDataSource</code><br>
     * <i>native declaration : NSComboBoxCell.h:56</i>
     */
    public abstract boolean usesDataSource();

    /**
     * Original signature : <code>-(void)scrollItemAtIndexToTop:(NSInteger)</code><br>
     * <i>native declaration : NSComboBoxCell.h:58</i>
     */
    public abstract void scrollItemAtIndexToTop(org.rococoa.cocoa.foundation.NSInteger index);

    /**
     * Original signature : <code>-(void)scrollItemAtIndexToVisible:(NSInteger)</code><br>
     * <i>native declaration : NSComboBoxCell.h:59</i>
     */
    public abstract void scrollItemAtIndexToVisible(org.rococoa.cocoa.foundation.NSInteger index);

    /**
     * Original signature : <code>-(void)selectItemAtIndex:(NSInteger)</code><br>
     * <i>native declaration : NSComboBoxCell.h:61</i>
     */
    public abstract void selectItemAtIndex(org.rococoa.cocoa.foundation.NSInteger index);

    /**
     * Original signature : <code>-(void)deselectItemAtIndex:(NSInteger)</code><br>
     * <i>native declaration : NSComboBoxCell.h:62</i>
     */
    public abstract void deselectItemAtIndex(org.rococoa.cocoa.foundation.NSInteger index);

    /**
     * Original signature : <code>-(NSInteger)indexOfSelectedItem</code><br>
     * <i>native declaration : NSComboBoxCell.h:63</i>
     */
    public abstract org.rococoa.cocoa.foundation.NSInteger indexOfSelectedItem();

    /**
     * Original signature : <code>-(NSInteger)numberOfItems</code><br>
     * <i>native declaration : NSComboBoxCell.h:64</i>
     */
    public abstract org.rococoa.cocoa.foundation.NSInteger numberOfItems();

    /**
     * Original signature : <code>-(BOOL)completes</code><br>
     * <i>native declaration : NSComboBoxCell.h:66</i>
     */
    public abstract boolean completes();

    /**
     * Original signature : <code>-(void)setCompletes:(BOOL)</code><br>
     * <i>native declaration : NSComboBoxCell.h:67</i>
     */
    public abstract void setCompletes(boolean completes);

    /**
     * Original signature : <code>-(NSString*)completedString:(NSString*)</code><br>
     * <i>native declaration : NSComboBoxCell.h:68</i>
     */
    public abstract org.rococoa.cocoa.foundation.NSString completedString(org.rococoa.cocoa.foundation.NSString string);

    /**
     * These two methods can only be used when usesDataSource is YES<br>
     * Original signature : <code>-(id<NSComboBoxCellDataSource>)dataSource</code><br>
     * <i>native declaration : NSComboBoxCell.h:71</i>
     */
    public abstract Object dataSource();

    /**
     * Original signature : <code>-(void)setDataSource:(id<NSComboBoxCellDataSource>)</code><br>
     * <i>native declaration : NSComboBoxCell.h:72</i>
     */
    public abstract void setDataSource(Object aSource);

    /**
     * These methods can only be used when usesDataSource is NO<br>
     * Original signature : <code>-(void)addItemWithObjectValue:(id)</code><br>
     * <i>native declaration : NSComboBoxCell.h:75</i>
     */
    public abstract void addItemWithObjectValue(org.rococoa.ObjCObject object);

    /**
     * Original signature : <code>-(void)addItemsWithObjectValues:(NSArray*)</code><br>
     * <i>native declaration : NSComboBoxCell.h:76</i>
     */
    public abstract void addItemsWithObjectValues(org.rococoa.cocoa.foundation.NSArray objects);

    /**
     * Original signature : <code>-(void)insertItemWithObjectValue:(id) atIndex:(NSInteger)</code><br>
     * <i>native declaration : NSComboBoxCell.h:77</i>
     */
    public abstract void insertItemWithObjectValue_atIndex(org.rococoa.ObjCObject object, org.rococoa.cocoa.foundation.NSInteger index);

    /**
     * Original signature : <code>-(void)removeItemWithObjectValue:(id)</code><br>
     * <i>native declaration : NSComboBoxCell.h:78</i>
     */
    public abstract void removeItemWithObjectValue(org.rococoa.ObjCObject object);

    /**
     * Original signature : <code>-(void)removeItemAtIndex:(NSInteger)</code><br>
     * <i>native declaration : NSComboBoxCell.h:79</i>
     */
    public abstract void removeItemAtIndex(org.rococoa.cocoa.foundation.NSInteger index);

    /**
     * Original signature : <code>-(void)removeAllItems</code><br>
     * <i>native declaration : NSComboBoxCell.h:80</i>
     */
    public abstract void removeAllItems();

    /**
     * Original signature : <code>-(void)selectItemWithObjectValue:(id)</code><br>
     * <i>native declaration : NSComboBoxCell.h:81</i>
     */
    public abstract void selectItemWithObjectValue(org.rococoa.ObjCObject object);

    /**
     * Original signature : <code>-(id)itemObjectValueAtIndex:(NSInteger)</code><br>
     * <i>native declaration : NSComboBoxCell.h:82</i>
     */
    public abstract org.rococoa.cocoa.foundation.NSObject itemObjectValueAtIndex(org.rococoa.cocoa.foundation.NSInteger index);

    /**
     * Original signature : <code>-(id)objectValueOfSelectedItem</code><br>
     * <i>native declaration : NSComboBoxCell.h:83</i>
     */
    public abstract org.rococoa.cocoa.foundation.NSObject objectValueOfSelectedItem();

    /**
     * Original signature : <code>-(NSInteger)indexOfItemWithObjectValue:(id)</code><br>
     * <i>native declaration : NSComboBoxCell.h:84</i>
     */
    public abstract org.rococoa.cocoa.foundation.NSInteger indexOfItemWithObjectValue(org.rococoa.ObjCObject object);

    /**
     * Original signature : <code>-(NSArray*)objectValues</code><br>
     * <i>native declaration : NSComboBoxCell.h:85</i>
     */
    public abstract org.rococoa.cocoa.foundation.NSArray objectValues();
    /// <i>native declaration : NSComboBoxCell.h:18</i>
}