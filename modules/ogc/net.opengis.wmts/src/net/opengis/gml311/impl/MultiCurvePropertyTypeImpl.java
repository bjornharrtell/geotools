/**
 */
package net.opengis.gml311.impl;

import net.opengis.gml311.Gml311Package;
import net.opengis.gml311.MultiCurvePropertyType;
import net.opengis.gml311.MultiCurveType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.w3.xlink.ActuateType;
import org.w3.xlink.ShowType;
import org.w3.xlink.TypeType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Multi Curve Property Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link net.opengis.gml311.impl.MultiCurvePropertyTypeImpl#getMultiCurve <em>Multi Curve</em>}</li>
 *   <li>{@link net.opengis.gml311.impl.MultiCurvePropertyTypeImpl#getActuate <em>Actuate</em>}</li>
 *   <li>{@link net.opengis.gml311.impl.MultiCurvePropertyTypeImpl#getArcrole <em>Arcrole</em>}</li>
 *   <li>{@link net.opengis.gml311.impl.MultiCurvePropertyTypeImpl#getHref <em>Href</em>}</li>
 *   <li>{@link net.opengis.gml311.impl.MultiCurvePropertyTypeImpl#getRemoteSchema <em>Remote Schema</em>}</li>
 *   <li>{@link net.opengis.gml311.impl.MultiCurvePropertyTypeImpl#getRole <em>Role</em>}</li>
 *   <li>{@link net.opengis.gml311.impl.MultiCurvePropertyTypeImpl#getShow <em>Show</em>}</li>
 *   <li>{@link net.opengis.gml311.impl.MultiCurvePropertyTypeImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link net.opengis.gml311.impl.MultiCurvePropertyTypeImpl#getType <em>Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MultiCurvePropertyTypeImpl extends MinimalEObjectImpl.Container implements MultiCurvePropertyType {
    /**
     * The cached value of the '{@link #getMultiCurve() <em>Multi Curve</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMultiCurve()
     * @generated
     * @ordered
     */
    protected MultiCurveType multiCurve;

    /**
     * The default value of the '{@link #getActuate() <em>Actuate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActuate()
     * @generated
     * @ordered
     */
    protected static final ActuateType ACTUATE_EDEFAULT = ActuateType.ON_LOAD_LITERAL;

    /**
     * The cached value of the '{@link #getActuate() <em>Actuate</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getActuate()
     * @generated
     * @ordered
     */
    protected ActuateType actuate = ACTUATE_EDEFAULT;

    /**
     * This is true if the Actuate attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean actuateESet;

    /**
     * The default value of the '{@link #getArcrole() <em>Arcrole</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArcrole()
     * @generated
     * @ordered
     */
    protected static final String ARCROLE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getArcrole() <em>Arcrole</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getArcrole()
     * @generated
     * @ordered
     */
    protected String arcrole = ARCROLE_EDEFAULT;

    /**
     * The default value of the '{@link #getHref() <em>Href</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHref()
     * @generated
     * @ordered
     */
    protected static final String HREF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getHref() <em>Href</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getHref()
     * @generated
     * @ordered
     */
    protected String href = HREF_EDEFAULT;

    /**
     * The default value of the '{@link #getRemoteSchema() <em>Remote Schema</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRemoteSchema()
     * @generated
     * @ordered
     */
    protected static final String REMOTE_SCHEMA_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRemoteSchema() <em>Remote Schema</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRemoteSchema()
     * @generated
     * @ordered
     */
    protected String remoteSchema = REMOTE_SCHEMA_EDEFAULT;

    /**
     * The default value of the '{@link #getRole() <em>Role</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRole()
     * @generated
     * @ordered
     */
    protected static final String ROLE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRole() <em>Role</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRole()
     * @generated
     * @ordered
     */
    protected String role = ROLE_EDEFAULT;

    /**
     * The default value of the '{@link #getShow() <em>Show</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShow()
     * @generated
     * @ordered
     */
    protected static final ShowType SHOW_EDEFAULT = ShowType.NEW_LITERAL;

    /**
     * The cached value of the '{@link #getShow() <em>Show</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getShow()
     * @generated
     * @ordered
     */
    protected ShowType show = SHOW_EDEFAULT;

    /**
     * This is true if the Show attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean showESet;

    /**
     * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTitle()
     * @generated
     * @ordered
     */
    protected static final String TITLE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTitle()
     * @generated
     * @ordered
     */
    protected String title = TITLE_EDEFAULT;

    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final TypeType TYPE_EDEFAULT = TypeType.SIMPLE_LITERAL;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected TypeType type = TYPE_EDEFAULT;

    /**
     * This is true if the Type attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean typeESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected MultiCurvePropertyTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return Gml311Package.eINSTANCE.getMultiCurvePropertyType();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public MultiCurveType getMultiCurve() {
        return multiCurve;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetMultiCurve(MultiCurveType newMultiCurve, NotificationChain msgs) {
        MultiCurveType oldMultiCurve = multiCurve;
        multiCurve = newMultiCurve;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__MULTI_CURVE, oldMultiCurve, newMultiCurve);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setMultiCurve(MultiCurveType newMultiCurve) {
        if (newMultiCurve != multiCurve) {
            NotificationChain msgs = null;
            if (multiCurve != null)
                msgs = ((InternalEObject)multiCurve).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Gml311Package.MULTI_CURVE_PROPERTY_TYPE__MULTI_CURVE, null, msgs);
            if (newMultiCurve != null)
                msgs = ((InternalEObject)newMultiCurve).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Gml311Package.MULTI_CURVE_PROPERTY_TYPE__MULTI_CURVE, null, msgs);
            msgs = basicSetMultiCurve(newMultiCurve, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__MULTI_CURVE, newMultiCurve, newMultiCurve));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ActuateType getActuate() {
        return actuate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setActuate(ActuateType newActuate) {
        ActuateType oldActuate = actuate;
        actuate = newActuate == null ? ACTUATE_EDEFAULT : newActuate;
        boolean oldActuateESet = actuateESet;
        actuateESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ACTUATE, oldActuate, actuate, !oldActuateESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetActuate() {
        ActuateType oldActuate = actuate;
        boolean oldActuateESet = actuateESet;
        actuate = ACTUATE_EDEFAULT;
        actuateESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ACTUATE, oldActuate, ACTUATE_EDEFAULT, oldActuateESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetActuate() {
        return actuateESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getArcrole() {
        return arcrole;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setArcrole(String newArcrole) {
        String oldArcrole = arcrole;
        arcrole = newArcrole;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ARCROLE, oldArcrole, arcrole));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getHref() {
        return href;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setHref(String newHref) {
        String oldHref = href;
        href = newHref;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__HREF, oldHref, href));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRemoteSchema() {
        return remoteSchema;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRemoteSchema(String newRemoteSchema) {
        String oldRemoteSchema = remoteSchema;
        remoteSchema = newRemoteSchema;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__REMOTE_SCHEMA, oldRemoteSchema, remoteSchema));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRole() {
        return role;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRole(String newRole) {
        String oldRole = role;
        role = newRole;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ROLE, oldRole, role));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ShowType getShow() {
        return show;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setShow(ShowType newShow) {
        ShowType oldShow = show;
        show = newShow == null ? SHOW_EDEFAULT : newShow;
        boolean oldShowESet = showESet;
        showESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__SHOW, oldShow, show, !oldShowESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetShow() {
        ShowType oldShow = show;
        boolean oldShowESet = showESet;
        show = SHOW_EDEFAULT;
        showESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__SHOW, oldShow, SHOW_EDEFAULT, oldShowESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetShow() {
        return showESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setTitle(String newTitle) {
        String oldTitle = title;
        title = newTitle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__TITLE, oldTitle, title));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public TypeType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setType(TypeType newType) {
        TypeType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        boolean oldTypeESet = typeESet;
        typeESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__TYPE, oldType, type, !oldTypeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetType() {
        TypeType oldType = type;
        boolean oldTypeESet = typeESet;
        type = TYPE_EDEFAULT;
        typeESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, Gml311Package.MULTI_CURVE_PROPERTY_TYPE__TYPE, oldType, TYPE_EDEFAULT, oldTypeESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetType() {
        return typeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__MULTI_CURVE:
                return basicSetMultiCurve(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__MULTI_CURVE:
                return getMultiCurve();
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ACTUATE:
                return getActuate();
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ARCROLE:
                return getArcrole();
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__HREF:
                return getHref();
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__REMOTE_SCHEMA:
                return getRemoteSchema();
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ROLE:
                return getRole();
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__SHOW:
                return getShow();
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__TITLE:
                return getTitle();
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__TYPE:
                return getType();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__MULTI_CURVE:
                setMultiCurve((MultiCurveType)newValue);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ACTUATE:
                setActuate((ActuateType)newValue);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ARCROLE:
                setArcrole((String)newValue);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__HREF:
                setHref((String)newValue);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__REMOTE_SCHEMA:
                setRemoteSchema((String)newValue);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ROLE:
                setRole((String)newValue);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__SHOW:
                setShow((ShowType)newValue);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__TITLE:
                setTitle((String)newValue);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__TYPE:
                setType((TypeType)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__MULTI_CURVE:
                setMultiCurve((MultiCurveType)null);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ACTUATE:
                unsetActuate();
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ARCROLE:
                setArcrole(ARCROLE_EDEFAULT);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__HREF:
                setHref(HREF_EDEFAULT);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__REMOTE_SCHEMA:
                setRemoteSchema(REMOTE_SCHEMA_EDEFAULT);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ROLE:
                setRole(ROLE_EDEFAULT);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__SHOW:
                unsetShow();
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__TITLE:
                setTitle(TITLE_EDEFAULT);
                return;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__TYPE:
                unsetType();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__MULTI_CURVE:
                return multiCurve != null;
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ACTUATE:
                return isSetActuate();
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ARCROLE:
                return ARCROLE_EDEFAULT == null ? arcrole != null : !ARCROLE_EDEFAULT.equals(arcrole);
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__HREF:
                return HREF_EDEFAULT == null ? href != null : !HREF_EDEFAULT.equals(href);
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__REMOTE_SCHEMA:
                return REMOTE_SCHEMA_EDEFAULT == null ? remoteSchema != null : !REMOTE_SCHEMA_EDEFAULT.equals(remoteSchema);
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__ROLE:
                return ROLE_EDEFAULT == null ? role != null : !ROLE_EDEFAULT.equals(role);
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__SHOW:
                return isSetShow();
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__TITLE:
                return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
            case Gml311Package.MULTI_CURVE_PROPERTY_TYPE__TYPE:
                return isSetType();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (actuate: ");
        if (actuateESet) result.append(actuate); else result.append("<unset>");
        result.append(", arcrole: ");
        result.append(arcrole);
        result.append(", href: ");
        result.append(href);
        result.append(", remoteSchema: ");
        result.append(remoteSchema);
        result.append(", role: ");
        result.append(role);
        result.append(", show: ");
        if (showESet) result.append(show); else result.append("<unset>");
        result.append(", title: ");
        result.append(title);
        result.append(", type: ");
        if (typeESet) result.append(type); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //MultiCurvePropertyTypeImpl
