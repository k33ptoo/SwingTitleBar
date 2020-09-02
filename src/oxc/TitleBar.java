package oxc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import utils.ComponentMoverUtil;
import utils.ComponentResizerUtil;

public final class TitleBar extends javax.swing.JPanel {

    public static int titleBarHeight = 28;
    private Color backgroundColor = Color.white;
    private ImageIcon frameIcon;
    private ImageIcon closeIcon = new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/close_small_dark.png"));
    private ImageIcon minimizeIcon = new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/minimize_small_dark.png"));
    private ImageIcon maximizeIcon = new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/maximize_small_dark.png"));
    private ImageIcon hoverCloseIcon = new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/close_small_hover.png"));
    private ImageIcon hoverMinimizeIcon = new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/minimize_small_hover.png"));
    private ImageIcon hoverMaximizeIcon = new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/maximize_small_hover.png"));
    private boolean isDark = false;

    //get external frame
    private JFrame extFrame;

    //--------------------------------------------------
    public TitleBar() {
        initComponents();
        this.setSize(200, getTitleBarHeight());
        resetButtonUI();
        //check if user is running Mac then move icons.
        if (!isMac()) {
            //move close icons to the left
            pnlRightInsert.remove(btnClose);
            pnlRightInsert.remove(btnMinimize);
            pnlRightInsert.remove(btnMaximize);
            pnlRightInsert.remove(lblFrameIcon);
            pnlRightInsert.remove(lblFrameTitle);
            pnlLeftInsert.add(btnClose, BorderLayout.WEST);
            pnlLeftInsert.add(btnMaximize, BorderLayout.EAST);
            pnlLeftInsert.add(btnMinimize, BorderLayout.CENTER);
            pnlRightInsert.add(lblFrameTitle, BorderLayout.CENTER);
            pnlRightInsert.add(lblFrameIcon, BorderLayout.EAST);

        }

        //setIcons
        btnClose.setIcon(getCloseIcon());
        btnMinimize.setIcon(getMinimizeIcon());
        btnMaximize.setIcon(getMaximizeIcon());
        btnClose.setRolloverIcon(getHoverCloseIcon());
        btnMinimize.setRolloverIcon(getHoverMinimizeIcon());
        btnMaximize.setRolloverIcon(getHoverMaximizeIcon());
        lblFrameIcon.setIcon(getFrameIcon());
        SwingUtilities.invokeLater(() -> {
            ComponentMoverUtil.moveFrame(getEFrame(), this);
            ComponentResizerUtil cr = new ComponentResizerUtil();
            getEFrame().getRootPane().setBorder(new LineBorder(Color.gray));
            cr.setSnapSize(new Dimension(5, 5));
            cr.setDragInsets(new Insets(5, 5, 5, 5));
            cr.registerComponent(getEFrame());

            //apply background and icons on color change
            for (Component c : getAllComponents(this)) {
                c.setBackground(getBackgroundColor());
            }
        });

    }

    //reset button ui
    public final void resetButtonUI() {
        JButton[] allBtns = {btnMinimize, btnMaximize, btnClose};
        for (JButton btns : allBtns) {
            btns.setUI(new BasicButtonUI());
            btns.setText("<html><font color=" + (btns.isEnabled() ? "white" : "silver") + ">" + btns.getText() + "</font></html>");
            if ("optionButtons".equals(btns.getName())) {
                btns.setBorder(new EmptyBorder(new Insets(0, 20, 0, 0)));
                btns.addMouseListener(hoverListener);
            }
        }
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public static int getTitleBarHeight() {
        return titleBarHeight;
    }

    public static void setTitleBarHeight(int titleBarHeight) {
        TitleBar.titleBarHeight = titleBarHeight;
    }

    public JFrame getEFrame() {
        return extFrame;
    }

    public void setEFrame(JFrame extFrame) {
        this.extFrame = extFrame;
    }

    public void setFrameTitle(String title) {
        lblFrameTitle.setText(title);
    }

    public String getFrameTitle() {
        return lblFrameTitle.getText();
    }

    public ImageIcon getFrameIcon() {
        return frameIcon;
    }

    public void setFrameIcon(ImageIcon frameIcon) {
        this.frameIcon = frameIcon;
    }

    public ImageIcon getCloseIcon() {
        return closeIcon;
    }

    public void setCloseIcon(ImageIcon closeIcon) {
        this.closeIcon = closeIcon;
    }

    public ImageIcon getMinimizeIcon() {
        return minimizeIcon;
    }

    public void setMinimizeIcon(ImageIcon minimizeIcon) {
        this.minimizeIcon = minimizeIcon;
    }

    public ImageIcon getMaximizeIcon() {
        return maximizeIcon;
    }

    public void setMaximizeIcon(ImageIcon maximizeIcon) {
        this.maximizeIcon = maximizeIcon;
    }

    public ImageIcon getHoverCloseIcon() {
        return hoverCloseIcon;
    }

    public void setHoverCloseIcon(ImageIcon hoverCloseIcon) {
        this.hoverCloseIcon = hoverCloseIcon;
    }

    public ImageIcon getHoverMinimizeIcon() {
        return hoverMinimizeIcon;
    }

    public void setHoverMinimizeIcon(ImageIcon hoverMinimizeIcon) {
        this.hoverMinimizeIcon = hoverMinimizeIcon;
    }

    public ImageIcon getHoverMaximizeIcon() {
        return hoverMaximizeIcon;
    }

    public void setHoverMaximizeIcon(ImageIcon hoverMaximizeIcon) {
        this.hoverMaximizeIcon = hoverMaximizeIcon;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLeft = new javax.swing.JPanel();
        pnlLeftInsert = new javax.swing.JPanel();
        lblFrameIcon = new javax.swing.JLabel();
        lblFrameTitle = new javax.swing.JLabel();
        pnlRight = new javax.swing.JPanel();
        pnlRightInsert = new javax.swing.JPanel();
        btnMinimize = new javax.swing.JButton();
        btnMaximize = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(200, 20));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(640, 30));
        setLayout(new java.awt.BorderLayout());

        pnlLeft.setOpaque(false);
        pnlLeft.setRequestFocusEnabled(false);
        pnlLeft.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 5));

        pnlLeftInsert.setMinimumSize(new java.awt.Dimension(0, 0));
        pnlLeftInsert.setLayout(new java.awt.BorderLayout());

        lblFrameIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFrameIcon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblFrameIcon.setPreferredSize(null);
        pnlLeftInsert.add(lblFrameIcon, java.awt.BorderLayout.WEST);

        lblFrameTitle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pnlLeftInsert.add(lblFrameTitle, java.awt.BorderLayout.CENTER);

        pnlLeft.add(pnlLeftInsert);

        add(pnlLeft, java.awt.BorderLayout.WEST);

        pnlRight.setRequestFocusEnabled(false);
        pnlRight.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 2, 1));

        pnlRightInsert.setLayout(new java.awt.BorderLayout(10, 0));

        btnMinimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/minimize_small_dark.png"))); // NOI18N
        btnMinimize.setBorderPainted(false);
        btnMinimize.setPreferredSize(new java.awt.Dimension(24, 24));
        btnMinimize.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/minimize_small_hover.png"))); // NOI18N
        btnMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinimizeActionPerformed(evt);
            }
        });
        pnlRightInsert.add(btnMinimize, java.awt.BorderLayout.WEST);

        btnMaximize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/maximize_small_dark.png"))); // NOI18N
        btnMaximize.setBorderPainted(false);
        btnMaximize.setPreferredSize(new java.awt.Dimension(24, 24));
        btnMaximize.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/maximize_small_hover.png"))); // NOI18N
        btnMaximize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaximizeActionPerformed(evt);
            }
        });
        pnlRightInsert.add(btnMaximize, java.awt.BorderLayout.CENTER);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/close_small_dark.png"))); // NOI18N
        btnClose.setBorderPainted(false);
        btnClose.setPreferredSize(new java.awt.Dimension(24, 24));
        btnClose.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/oxc/icons/close_small_hover.png"))); // NOI18N
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        pnlRightInsert.add(btnClose, java.awt.BorderLayout.EAST);

        pnlRight.add(pnlRightInsert);

        add(pnlRight, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        closeFrame(evt);//getExternalFrame().dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnMaximizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaximizeActionPerformed
        if (getEFrame().getExtendedState() == Frame.MAXIMIZED_BOTH) {
            getEFrame().setExtendedState(JFrame.NORMAL);
        } else {
            getEFrame().setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
    }//GEN-LAST:event_btnMaximizeActionPerformed

    private void btnMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinimizeActionPerformed
        getEFrame().setState(Frame.ICONIFIED);
    }//GEN-LAST:event_btnMinimizeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnMaximize;
    private javax.swing.JButton btnMinimize;
    public javax.swing.JLabel lblFrameIcon;
    private javax.swing.JLabel lblFrameTitle;
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlLeftInsert;
    private javax.swing.JPanel pnlRight;
    private javax.swing.JPanel pnlRightInsert;
    // End of variables declaration//GEN-END:variables

    private void closeFrame(java.awt.event.ActionEvent evt) {
        JFrame frame = getEFrame();
        WindowListener[] wlis = frame.getWindowListeners();
        if (wlis != null && wlis.length > 0) {
            //custom closing defined
            for (WindowListener wl : wlis) {
                if (wl instanceof java.awt.event.WindowAdapter) {//custom closing operation
                    java.awt.event.WindowEvent wevt = new java.awt.event.WindowEvent(frame, java.awt.event.WindowEvent.WINDOW_CLOSING);
                    wl.windowClosing(wevt);
                }
            }
        }
        frame.dispose();

    }

    //implement mouse hover effects
    private final MouseAdapter hoverListener = new MouseAdapter() {

        @Override
        public void mouseEntered(MouseEvent e
        ) {
            JButton btn = (JButton) e.getComponent();
            btn.setBackground(new Color(36, 44, 55));
        }

        @Override
        public void mouseExited(MouseEvent e
        ) {
            JButton btn = (JButton) e.getComponent();
            btn.setBackground(new Color(31, 38, 48));
        }

        @Override
        public void mousePressed(MouseEvent e) {
            AbstractButton btn = (AbstractButton) e.getComponent();
            btn.setBackground(btn.isEnabled() ? new Color(20, 25, 32) : new Color(20, 25, 32));
        }

    };

    //check for os
    private String os = System.getProperty("os.name").toLowerCase();

    private boolean isMac() {
        return (os.indexOf("mac") >= 0);
    }

    private List<Component> getAllComponents(Container... parents) {
        List<Component> cs = new ArrayList<>();
        for (Container parent : parents) {
            for (Component c : parent.getComponents()) {
                cs.add(c);
                if (c instanceof Container) {
                    cs.addAll(getAllComponents((Container) c));
                }
            }
        }
        return cs;
    }

}
