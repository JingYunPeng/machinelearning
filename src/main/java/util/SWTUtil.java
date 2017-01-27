package util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

/**
 * Created by jing on 2017/1/24.\
 */
public class SWTUtil {
    public static Button createButton(Composite composite, String text, final Listener listener) {
        Button button = new Button(composite, SWT.PUSH);
        button.setText(text);
        button.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent selectionEvent) {
                listener.onClick();
            }
        });
        return button;
    }

    public static interface Listener {
        void onClick();
    }

    public static GridData createGridData(int xspan, int yspan) {
        return createGridData(xspan, yspan, -1, -1);
    }

    public static GridData createGridData(int xspan, int yspan, int xmin, int ymin) {
        GridData result = new GridData();
        result.horizontalSpan = xspan;
        result.verticalSpan = yspan;
        result.widthHint = xmin;
        result.heightHint = ymin;
        return result;
    }

    public static void pack(int max, Control... controls) {
        int size = controls.length;
        if (size > max) {
            throw new RuntimeException();
        }
        for (int i = 0; i < size - 1; i++) {
            controls[i].setLayoutData(createGridData(1, 1));
        }
        controls[size - 1].setLayoutData(createGridData(max - size, 1));
    }
}

