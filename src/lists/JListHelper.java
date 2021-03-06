package lists;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Contains static methods with cool functionality for JLists. Requires that the
 * JLists use DefaultListModels.
 *
 * @author Jacob Dorman
 */
public class JListHelper {

    /**
     * Links two JLists so that if an element is clicked on one it is moved to
     * the other list.
     *
     * @param firstList the first JList
     * @param secondList the second JList
     */
    public static void registerLinkedJListListener(final JList firstList, final JList secondList) {
        firstList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        secondList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final DefaultListModel firstDataModel = (DefaultListModel) firstList.getModel();
        final DefaultListModel secondDataModel = (DefaultListModel) secondList.getModel();
        firstList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Object selectedValue = firstList.getSelectedValue();
                    if (selectedValue != null) {
                        firstDataModel.removeElement(selectedValue);
                        secondDataModel.addElement(selectedValue);
                    }
                }
            }
        });
        secondList.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    Object selectedValue = secondList.getSelectedValue();
                    if (selectedValue != null) {
                        secondDataModel.removeElement(selectedValue);
                        firstDataModel.addElement(selectedValue);
                    }
                }
            }
        });
    }
}
