/*
 * JGrass - Free Open Source Java GIS http://www.jgrass.org 
 * (C) HydroloGIS - www.hydrologis.com 
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * (http://www.eclipse.org/legal/epl-v10.html), and the HydroloGIS BSD
 * License v1.0 (http://udig.refractions.net/files/hsd3-v10.html).
 */
package eu.udig.style.advanced.lines.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import eu.udig.style.advanced.common.ParameterComposite;
import eu.udig.style.advanced.common.IStyleChangesListener.STYLEEVENTTYPE;
import eu.udig.style.advanced.common.styleattributeclasses.RuleWrapper;
import eu.udig.style.advanced.common.styleattributeclasses.SymbolizerWrapper;
import eu.udig.style.advanced.internal.Messages;
import eu.udig.style.advanced.utils.Utilities;

/**
 * A composite that holds widgets for polygon general parameter setting.
 * 
 * @author Andrea Antonello (www.hydrologis.com)
 */
public class LineGeneralParametersComposite extends ParameterComposite {

    private final Composite parent;
    private final String[] numericAttributesArrays;

    private Text nameText;
    private Spinner xOffsetSpinner;
    private Spinner yOffsetSpinner;
    private Text maxScaleText;
    private Text minScaleText;

    private Composite mainComposite;

    public LineGeneralParametersComposite( Composite parent, String[] numericAttributesArrays ) {
        this.parent = parent;
        this.numericAttributesArrays = numericAttributesArrays;
    }

    public Composite getComposite() {
        return mainComposite;
    }

    /**
     * Initialize the panel with pre-existing values.
     * 
     * @param ruleWrapper the {@link RuleWrapper}.
     */
    public void init( RuleWrapper ruleWrapper ) {
        SymbolizerWrapper symbolizersWrapper = ruleWrapper.getGeometrySymbolizersWrapper();

        mainComposite = new Composite(parent, SWT.NONE);
        mainComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        mainComposite.setLayout(new GridLayout(3, true));

        // rule name
        Label nameLabel = new Label(mainComposite, SWT.NONE);
        nameLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false));
        nameLabel.setText(Messages.LineGeneralParametersComposite_0);
        nameText = new Text(mainComposite, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
        GridData nameTextGD = new GridData(SWT.FILL, SWT.CENTER, true, false);
        nameTextGD.horizontalSpan = 2;
        nameText.setLayoutData(nameTextGD);
        nameText.setText(ruleWrapper.getName());
        nameText.addFocusListener(this);

        Label offsetLabel = new Label(mainComposite, SWT.NONE);
        GridData offsetLabelGD = new GridData(SWT.FILL, SWT.CENTER, true, false);
        offsetLabel.setLayoutData(offsetLabelGD);
        offsetLabel.setText(Messages.LineGeneralParametersComposite_1);

        String xOffset = symbolizersWrapper.getxOffset();
        String yOffset = symbolizersWrapper.getyOffset();
        Integer tmpXOffset = Utilities.isNumber(xOffset, Integer.class);
        Integer tmpYOffset = Utilities.isNumber(yOffset, Integer.class);
        if (tmpXOffset == null || tmpYOffset == null) {
            tmpXOffset = 0;
            tmpYOffset = 0;
        }
        xOffsetSpinner = new Spinner(mainComposite, SWT.BORDER);
        xOffsetSpinner.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        xOffsetSpinner.setMaximum(Utilities.OFFSET_MAX);
        xOffsetSpinner.setMinimum(Utilities.OFFSET_MIN);
        xOffsetSpinner.setIncrement(Utilities.OFFSET_STEP);
        xOffsetSpinner.setSelection((int) (10 * tmpXOffset));
        xOffsetSpinner.setDigits(1);
        xOffsetSpinner.addSelectionListener(this);

        yOffsetSpinner = new Spinner(mainComposite, SWT.BORDER);
        yOffsetSpinner.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        yOffsetSpinner.setMaximum(Utilities.OFFSET_MAX);
        yOffsetSpinner.setMinimum(Utilities.OFFSET_MIN);
        yOffsetSpinner.setIncrement(Utilities.OFFSET_STEP);
        yOffsetSpinner.setSelection((int) (10 * tmpYOffset));
        yOffsetSpinner.setDigits(1);
        yOffsetSpinner.addSelectionListener(this);

        Label maxScaleLabel = new Label(mainComposite, SWT.NONE);
        GridData maxScaleLabelGD = new GridData(SWT.FILL, SWT.CENTER, true, false);
        maxScaleLabel.setLayoutData(maxScaleLabelGD);
        maxScaleLabel.setText(Messages.LineGeneralParametersComposite_2);
        maxScaleText = new Text(mainComposite, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
        GridData maxScaleTextSIMPLEGD = new GridData(SWT.FILL, SWT.CENTER, true, false);
        maxScaleTextSIMPLEGD.horizontalSpan = 2;
        maxScaleText.setLayoutData(maxScaleTextSIMPLEGD);
        maxScaleText.setText(ruleWrapper.getMaxScale());
        maxScaleText.addKeyListener(this);

        Label minScaleLabel = new Label(mainComposite, SWT.NONE);
        GridData minScaleLabelGD = new GridData(SWT.FILL, SWT.CENTER, true, false);
        minScaleLabel.setLayoutData(minScaleLabelGD);
        minScaleLabel.setText(Messages.LineGeneralParametersComposite_3);
        minScaleText = new Text(mainComposite, SWT.SINGLE | SWT.LEAD | SWT.BORDER);
        GridData mainScaleTextSIMPLEGD = new GridData(SWT.FILL, SWT.CENTER, true, false);
        mainScaleTextSIMPLEGD.horizontalSpan = 2;
        minScaleText.setLayoutData(mainScaleTextSIMPLEGD);
        minScaleText.setText(ruleWrapper.getMinScale());
        minScaleText.addKeyListener(this);
    }

    /**
     * Update the panel.
     * 
     * @param ruleWrapper the {@link RuleWrapper}.
     */
    public void update( RuleWrapper ruleWrapper ) {
        SymbolizerWrapper symbolizersWrapper = ruleWrapper.getGeometrySymbolizersWrapper();

        nameText.setText(ruleWrapper.getName());

        // offset
        String xOffset = symbolizersWrapper.getxOffset();
        String yOffset = symbolizersWrapper.getyOffset();
        Double tmpXOffset = Utilities.isNumber(xOffset, Double.class);
        Double tmpYOffset = Utilities.isNumber(yOffset, Double.class);
        if (tmpXOffset == null || tmpYOffset == null) {
            tmpXOffset = 0.0;
            tmpYOffset = 0.0;
        }
        xOffsetSpinner.setSelection((int) (10 * tmpXOffset));
        yOffsetSpinner.setSelection((int) (10 * tmpYOffset));

        // scale
        Double maxScaleDouble = isDouble(ruleWrapper.getMaxScale());
        if (maxScaleDouble == null || Double.isInfinite(maxScaleDouble)) {
            maxScaleText.setText(""); //$NON-NLS-1$
        } else {
            maxScaleText.setText(String.valueOf(maxScaleDouble));
        }
        Double minScaleDouble = isDouble(ruleWrapper.getMinScale());
        if (minScaleDouble == null || minScaleDouble == 0) {
            minScaleText.setText(""); //$NON-NLS-1$
        } else {
            minScaleText.setText(String.valueOf(minScaleDouble));
        }
    }

    public void widgetSelected( SelectionEvent e ) {
        Object source = e.getSource();
        if (source.equals(xOffsetSpinner) || source.equals(yOffsetSpinner)) {
            double x = Utilities.getDoubleSpinnerSelection(xOffsetSpinner);
            double y = Utilities.getDoubleSpinnerSelection(yOffsetSpinner);
            
            String offsetStr = x + "," + y; //$NON-NLS-1$
            notifyListeners(offsetStr, false, STYLEEVENTTYPE.OFFSET);
        }
    }

    public void keyPressed( KeyEvent e ) {
    }

    public void keyReleased( KeyEvent e ) {
        Object source = e.getSource();
        if (source.equals(maxScaleText)) {
            String maxScale = maxScaleText.getText();
            notifyListeners(maxScale, false, STYLEEVENTTYPE.MAXSCALE);
        } else if (source.equals(minScaleText)) {
            String minScale = minScaleText.getText();
            notifyListeners(minScale, false, STYLEEVENTTYPE.MINSCALE);
        }
    }

    public void focusGained( FocusEvent e ) {
    }

    public void focusLost( FocusEvent e ) {
        Object source = e.getSource();
        if (source.equals(nameText)) {
            String text = nameText.getText();
            notifyListeners(text, false, STYLEEVENTTYPE.NAME);
        }
    }

}
