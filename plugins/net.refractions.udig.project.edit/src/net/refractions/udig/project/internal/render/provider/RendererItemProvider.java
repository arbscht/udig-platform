/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package net.refractions.udig.project.internal.render.provider;

import java.util.Collection;
import java.util.List;

import net.refractions.udig.project.internal.provider.ProjectEditPlugin;
import net.refractions.udig.project.internal.render.RenderPackage;
import net.refractions.udig.project.internal.render.Renderer;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link net.refractions.udig.project.internal.render.Renderer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class RendererItemProvider extends ItemProviderAdapter
        implements
            IEditingDomainItemProvider,
            IStructuredItemContentProvider,
            ITreeItemContentProvider,
            IItemLabelProvider,
            IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    public RendererItemProvider( AdapterFactory adapterFactory ) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors( Object object ) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addStatePropertyDescriptor(object);
            addNamePropertyDescriptor(object);
            addContextPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Name feature.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    protected void addNamePropertyDescriptor( Object object ) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(
                        ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Renderer_name_feature"), //$NON-NLS-1$
                        getString(
                                "_UI_PropertyDescriptor_description", "_UI_Renderer_name_feature", "_UI_Renderer_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        RenderPackage.Literals.RENDERER__NAME, true, false, false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the State feature.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    protected void addStatePropertyDescriptor( Object object ) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(
                        ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Renderer_state_feature"), //$NON-NLS-1$
                        getString(
                                "_UI_PropertyDescriptor_description", "_UI_Renderer_state_feature", "_UI_Renderer_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        RenderPackage.Literals.RENDERER__STATE, true, false, false,
                        ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Context feature.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    protected void addContextPropertyDescriptor( Object object ) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(
                        ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(),
                        getString("_UI_Renderer_context_feature"), //$NON-NLS-1$
                        getString(
                                "_UI_PropertyDescriptor_description", "_UI_Renderer_context_feature", "_UI_Renderer_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        RenderPackage.Literals.RENDERER__CONTEXT, true, false, true, null, null,
                        null));
    }

    /**
     * This returns Renderer.gif.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage( Object object ) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Renderer")); //$NON-NLS-1$
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected boolean shouldComposeCreationImage() {
        return true;
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @generated NOT
     */
    public String getText( Object object ) {
        String label = ((Renderer) object).getName();
        return label == null || label.length() == 0 ? "Renderer" : label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged( Notification notification ) {
        updateChildren(notification);

        switch( notification.getFeatureID(Renderer.class) ) {
        case RenderPackage.RENDERER__STATE:
        case RenderPackage.RENDERER__NAME:
            fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(),
                    false, true));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return ProjectEditPlugin.INSTANCE;
    }

}
