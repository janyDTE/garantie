/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beaucornu.garantie;

import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.behavior.KeyBinding;
import java.util.List;

/**
 *
 * @author Narayan G. Maharjan
 * @see <a href="http://www.blog.ngopal.com.np"> Blog </a>
 */
public class AutoFillTextBoxBehavior<T> extends BehaviorBase<AutoFillTextBox<T>> {

    public AutoFillTextBoxBehavior(AutoFillTextBox<T> textBox, List<KeyBinding> keys) {
        super(textBox, keys);
    }
}
