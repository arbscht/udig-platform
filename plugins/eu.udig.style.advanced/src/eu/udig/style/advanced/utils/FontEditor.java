/* uDig - User Friendly Desktop Internet GIS client
 * http://udig.refractions.net
 * (C) 2004-2012, Refractions Research Inc.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * (http://www.eclipse.org/legal/epl-v10.html), and the Refractions BSD
 * License v1.0 (http://udig.refractions.net/files/bsd3-v10.html).
 */
package eu.udig.style.advanced.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Shell;

import eu.udig.style.advanced.internal.Messages;

/**
 * <p>
 * A "button" with an icon/text that pops up the font window.
 * </p>
 * 
 * @author aalam
 * @author Andrea Antonello (www.hydrologis.com)
 * @since 0.6.0
 */
public class FontEditor {

    private Image fImage = null;
    private RGB fColorValue;
    private Color fColor[] = null;
    private Font fFont[] = null;
    private FontData[] fFontList = null;
    private Button fButton;
    private SelectionListener parentListener;
    final FontDialog labelFontDialog;

    public FontEditor(Composite parent) {
        this(parent, null);
    }
    
    public FontEditor( Composite parent, SelectionListener parentListener ) {
        this.parentListener = parentListener;
        fButton = new Button(parent, SWT.PUSH);

        fFont = new Font[1];
        fColor = new Color[1];

        fButton.setText(Messages.FontEditor_0);
        labelFontDialog = new FontDialog(new Shell());
        labelFontDialog.setText("Choose a Font"); //$NON-NLS-1$

        fButton.setImage(fImage);
        fButton.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected( SelectionEvent event ) {
                labelFontDialog.setRGB(fColorValue);
                labelFontDialog.setFontList(fFontList);

                if (labelFontDialog.open() == null)
                    return;
                if (fFont[0] != null)
                    fFont[0].dispose();
                FontData[] list = labelFontDialog.getFontList();
                if (list != null) {
                    fFont[0] = new Font(fButton.getDisplay(), list);
                    // set the text font here...
                    fFontList = list;
                }
                RGB rgb = labelFontDialog.getRGB();
                if (rgb != null) {
                    if (fColor[0] != null)
                        fColor[0].dispose();
                    fColor[0] = new Color(fButton.getDisplay(), rgb);
                    // set the text foreground color here...
                    fColorValue = rgb;
                    
                    fButton.setForeground(fColor[0]);
                }
                notifyParent(event);
            }
        });

        fButton.addDisposeListener(new DisposeListener(){
            public void widgetDisposed( DisposeEvent event ) {
                if (fImage != null) {
                    fImage.dispose();
                    fImage = null;
                }
                if (fColor != null) {
                    if (fColor[0] != null)
                        fColor[0].dispose();
                    fColor = null;
                }
                if (fFont != null) {
                    if (fFont[0] != null)
                        fFont[0].dispose();
                    fFont = null;
                }
            }
        });
    }
    
    public void setListener(SelectionListener parentListener) {
        this.parentListener = parentListener;
    }
    
    public void clearListener() {
        this.parentListener = null;
    }

    private void notifyParent( SelectionEvent event ) {
        if(parentListener != null) 
            parentListener.widgetSelected(event);
    }

    public Color getColor() {
        return fColor[0];
    }
    
    public java.awt.Color getAWTColor() {
        Color tmpColor = getColor();
        if(tmpColor == null) return null;
        return new java.awt.Color(tmpColor.getRed(), 
                tmpColor.getGreen(), tmpColor.getBlue());
    }

    /**
     * Returns the currently selected font object
     * 
     * @return Font
     */
    public Font getFont() {
        return fFont[0];
    }

    public FontData[] getFontList() {
        return fFontList;
    }

    public void setFontList( FontData[] list ) {
        if(list != null) {
            fFontList = list;
            fFont[0] = new Font(fButton.getDisplay(), list);
        	labelFontDialog.setFontList(list);
        }
    }

    public RGB getColorValue() {
        return fColorValue;
    }

    public void setColorValue( RGB rgb ) {
        fColorValue = rgb;
        fColor[0] = new Color(fButton.getDisplay(), rgb);
        labelFontDialog.setRGB(rgb);
        updateColorImage();
    }
    
    public void setColorValue(java.awt.Color colour) {
        if(colour != null) {
            setColorValue(new RGB(
                	colour.getRed(), colour.getGreen(), colour.getBlue()));
        }
    }

    public Button getButton() {
        return fButton;
    }

    private void updateColorImage() {
        /*
         * Display display= fButton.getDisplay(); GC gc= new GC(fImage);
         * gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK)); gc.drawRectangle(0, 2,
         * fExtent.x - 1, fExtent.y - 4); if (fColor != null) fColor.dispose(); fColor= new
         * Color(display, fColorValue); gc.setBackground(fColor); gc.fillRectangle(1, 3, fExtent.x -
         * 2, fExtent.y - 5); gc.dispose(); fButton.setImage(fImage);
         */
    }

    public void setEnabled(boolean enabled) {
        fButton.setEnabled(enabled);
    }
}
