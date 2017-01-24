package util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Created by jing on 2017/1/24.\
 *
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
}
